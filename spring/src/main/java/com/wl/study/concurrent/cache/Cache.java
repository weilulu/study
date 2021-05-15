package com.wl.study.concurrent.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author:weilu
 * @Date:2020/1/24 22:30
 * @Description:${Description}
 */
public class Cache<K,V> {
    private static final Logger LOG = Logger.getLogger(Cache.class.getName());


    private ConcurrentMap<K,V> cacheObjMap = new ConcurrentHashMap();
    private DelayQueue<DelayItem<Pair<K,V>>> q = new DelayQueue<>();
    private Thread daemonThread;
    public Cache(){
        Runnable daemonTask = new Runnable() {
            @Override
            public void run() {
                daemonCheck();
            }
        };
        daemonThread = new Thread(daemonTask);
        daemonThread.setDaemon(true);
        daemonThread.setName("Cache Daemon");
        daemonThread.start();
    }
    private void daemonCheck(){
        if(LOG.isLoggable(Level.INFO)){
            LOG.info("cache service started.");
        }
        for(;;){
            try {
                //在延迟队列里，如果时间没到，则会await当前操作，时间检查单位为nanoSecond
                DelayItem<Pair<K,V>> delayItem = q.take();
                if(delayItem != null){//说明已到了时间，则需要移除
                    Pair<K,V> pair = delayItem.getItem();
                    cacheObjMap.remove(pair.key,pair.value);
                }
            }catch(InterruptedException e){
                if (LOG.isLoggable(Level.SEVERE))
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                break;
            }
        }
        if (LOG.isLoggable(Level.INFO))
            LOG.info("cache service stopped.");
    }

    /**
     * 除了放入map外，还需要放入延迟队列里(有延迟时间)
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    public void put(K key, V value, long time, TimeUnit timeUnit){
        V oldObject = cacheObjMap.put(key, value);
        if(oldObject != null){//todo 如果之前存过，则将延迟队列之前的键值对移除，以当前时间为准
            q.remove(key);
        }
        long convert = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        q.put(new DelayItem<>(new Pair<>(key,value),convert));

    }
    public V get(K key){
        return cacheObjMap.get(key);
    }

    public static void main(String[] args) throws Exception{
        Cache<Integer,String> cache = new Cache<>();
        cache.put(1,"test",3,TimeUnit.SECONDS);//3s后缓存失效
        Thread.sleep(1000 * 2);
        System.out.println(cache.get(1));//test

        Thread.sleep(1000 * 2);
        System.out.println(cache.get(1));//null，２s后已经被移除了

    }
}
