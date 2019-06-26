package com.wl.study.watcher;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.wl.study.watcher.annotation.MonitorArgs;
import com.wl.study.watcher.annotation.MonitorMethod;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author:weilu
 * @Date: 2019/3/15 21:26
 */
public class RequestCounter {

    //private static final Logger logger = LoggerFactory.getLogger(RequestCounter.class);

    private static ExecutorService executorService = new ThreadPoolExecutor(10,
            20,1L, TimeUnit.MINUTES,
            new LinkedBlockingDeque<Runnable>(10000),new ThreadFactoryBuilder()
            .setNameFormat("wl-counter-%d")
            .setDaemon(true)
            .build(),new ThreadPoolExecutor.DiscardPolicy());
    public static MonitNode monitRoot = new MonitNode();

    public static final int maxMinutes = 60;
    public static final int countStep = 60;

    private static Map<Class, IArgsConvertor> convertorMap = new ConcurrentHashMap<Class, IArgsConvertor>();

    public static void record(final String method, final Object[] args, final MonitorMethod monitor){
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    MonitorArgs[] monitorArgs = monitor.monitorArgs();
                    Map<Integer,Set<RequestNode>> map = new HashMap<>();
                    for(MonitorArgs mrgs : monitorArgs){
                        System.out.println(JSON.toJSONString(mrgs));
                        if(mrgs.level() >= 100){
                            continue;
                        }
                        Object obj = StringUtils.isBlank(mrgs.path()) ? args[mrgs.index() - 1] : BeanUtils.getNestedProperty(args[mrgs.index() - 1], mrgs.path());
                        if(obj == null){
                            continue;
                        }
                        if(map.get(mrgs.level()) == null){
                            map.put(mrgs.level(),new HashSet<>());
                        }
                        map.get(mrgs.level()).add(new RequestNode(mrgs.alias(),obj.toString(),mrgs.topSize()));
                    }

                    RequestNode requestMethod = new RequestNode("",method,500);
                    if(map.get(0) == null){
                        map.put(0,new HashSet<>());
                    }
                    map.get(0).add(requestMethod);
                    for(int i=1;i<map.size();i++){
                        if(map.get(i) == null || map.get(i -1) == null){
                            break;
                        }
                        for(RequestNode parent : map.get(i - 1)){
                            for(RequestNode child : map.get(i)){
                                parent.getChildren().add(child);
                            }
                        }
                    }

                    long minute = System.currentTimeMillis() / (countStep * 1000);
                    recursiveMonitTree(requestMethod,monitRoot,minute);
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        });
    }

    private static void recursiveMonitTree(final RequestNode requestNode,MonitNode monitNode,long minuteNow)throws ExecutionException{
        System.out.println("requestNode:"+requestNode);
        MonitNode monitChild = monitNode.getChildren(requestNode.getTopSize()).get(requestNode.getKey(), new Callable<MonitNode>() {
            @Override
            public MonitNode call() throws Exception {
                MonitNode mn = new MonitNode();
                mn.setAlias(requestNode.getAlias());
                return mn;
            }
        });
        System.out.println("monitChild:"+monitChild);
        //以分钟为粒度记录访问的频次
        LongAdder count = monitChild.getHistory().get(minuteNow);
        if(count == null){
            clean(minuteNow,monitChild.getHistory());
            count = new LongAdder();
            monitChild.getHistory().put(minuteNow,count);
        }
        count.increment();

        for(RequestNode requestChild:requestNode.getChildren()){
            recursiveMonitTree(requestChild,monitNode,minuteNow);
        }
        System.out.println("recursiveMonitTree count:"+count);
    }

    private static void clean(Long now,Map<Long,LongAdder> history){
        for(Long time : history.keySet()){
            if(time + maxMinutes < now){
                history.remove(time);
            }
        }
    }

    /**
     * 解析参数的值
     * @param ma
     * @param argsContext
     * @param args
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     */
    public static String getArgsValue(MonitorArgs ma,Map<String,String> argsContext,Object[] args) throws IllegalAccessException,InvocationTargetException, NoSuchMethodException, InstantiationException{
        IArgsConvertor convertor = convertorMap.get(ma.convertor());
        if(convertor == null){
            try {
                convertor = ma.convertor().newInstance();
                convertorMap.put(ma.convertor(),convertor);
            }catch (Exception e){
                return null;
            }
        }
        Object obj;
        if(argsContext != null && StringUtils.isNotBlank(ma.key())){
            obj = argsContext.get(ma.key());
        }else {
            obj = StringUtils.isBlank(ma.path()) ? args[ma.index() - 1] : PropertyUtils.getNestedProperty(args[ma.index() - 1], ma.path());
        }
        return convertor.convert(obj);
    }

}
