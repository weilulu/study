package com.wl.study.watcher;

import com.wl.study.watcher.annotation.MonitorArgs;
import com.wl.study.watcher.annotation.MonitorMethod;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:weilu
 * @Date: 2019/3/21 18:12
 */
public class DoorKeeper {

    public static Map<String,List<LimitPolicy>> limitbook = new ConcurrentHashMap<>();

    static {

        List<LimitPolicy> limitPolicies = new ArrayList<>();
        LimitPolicy policy = new LimitPolicy();
        String method = "com.wl.study.watcher.test.impl.MonitorServiceImpl.methodCall";
        policy.setMethod(method);
        Map<String,String> condition = new HashMap<>();
        condition.put("appId","1");//具体的条件：值为1的appId.再比如venderId为432条件
        policy.setConditionMap(condition);
        policy.setThreadHold("60");
        limitPolicies.add(policy);
        limitbook.put(method,limitPolicies);
    }

    public static boolean ask4Access(String method, Object[] args, final Map<String,String> argsContext, MonitorMethod monitor){
        try{
            List<LimitPolicy> policies = limitbook.get(method);
            if(CollectionUtils.isNotEmpty(policies)){
                for(LimitPolicy policy : policies){
                    if(method.equals(policy.getMethod()) && !isAcquired(args,argsContext,monitor,policy)){
                        return false;
                    }
                }
            }
        }catch (Throwable e){

        }
        return true;
    }

    private static boolean isAcquired(Object[] args, final Map<String, String> argsContext, MonitorMethod monitor, LimitPolicy policy)throws Exception{
        if(policy.getConditionMap().size() == 0){
            //没有特有的条件，直接尝试获取到令牌
            boolean flag = policy.getLimiter().tryAcquire();
            return flag;
        }
        int matchCount = 0;
        for(MonitorArgs ma : monitor.monitorArgs()){
            String condition = policy.getConditionMap().get(ma.alias());//具体参数的值,这里的参数及值是通过配置的
            if(StringUtils.isNotBlank(condition)){
                String value = RequestCounter.getArgsValue(ma,argsContext,args);//同样是参数的值，这是在使用注解时指定的值
                //如果配置的值包含注解中指定的值
                if(value != null && Arrays.asList(StringUtils.split(condition,"|")).contains(value)){

                    if(LimitPolicy.JoinLogic.OR.name().equalsIgnoreCase(policy.getJoin())){
                        return policy.getLimiter().tryAcquire();
                    }
                    matchCount ++;
                }
            }
        }

        //如果配置的条件和注解里的条件一致，则取配置里的限制策略
        return matchCount == policy.getConditionMap().size() ? policy.getLimiter().tryAcquire() : true;
    }
}
