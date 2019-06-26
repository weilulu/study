package com.wl.study.watcher;

import com.wl.study.watcher.annotation.MonitorMethod;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:weilu
 * @Date: 2019/3/15 20:46
 */
@Service
@Aspect
@Order(Integer.MAX_VALUE)
public class MonitorHandler implements ApplicationContextAware {

    String limitMsg;

    private static Map<Class, IArgsContextFiller> fillerMap = new ConcurrentHashMap<Class, IArgsContextFiller>();

    @Before("@annotation(monitorMethod)")
    public void invoke(JoinPoint point, MonitorMethod monitorMethod)throws Exception{
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        //getCanonicalName返回包名+类名
        String methodName = point.getTarget().getClass().getCanonicalName() + "." + method.getName();
        if(StringUtils.isBlank(methodName)){
            methodName = methodName + "_" + monitorMethod.alias();
        }
        Map<String,String> argsContext = getArgsContext(monitorMethod);
        if(!DoorKeeper.ask4Access(methodName,point.getArgs(),argsContext,monitorMethod)){
            throw new Exception("Request is restricted!");
        }
        //对方法访问次数进行统计，然后在页面上进行展示，与上面的限流没有关系
        RequestCounter.record(methodName,point.getArgs(),monitorMethod);

    }

    public Map<String,String> getArgsContext(MonitorMethod monitorMethod){
        IArgsContextFiller filler = fillerMap.get(monitorMethod.filler());
        if(filler == null){
            try {
                filler = monitorMethod.filler().newInstance();
                fillerMap.put(monitorMethod.filler(),filler);
            }catch (Exception e){
                return null;
            }
        }
        return filler.fill();
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    public void setLimitMsg(String limitMsg){
        this.limitMsg = limitMsg;
    }
}
