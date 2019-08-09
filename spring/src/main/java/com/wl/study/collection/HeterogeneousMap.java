package com.wl.study.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:weilu
 * @Date:2019/8/9 13:45
 * @Description:异构Map，通过泛型和Class实现将不同类型放入Map中
 */
public class HeterogeneousMap {

    private Map<Class<?>,Object> heterMap = new HashMap<>();

    /**
     *
     * @param type
     * @param instance 为了确保value与key的类型一致，这里不能使用Object，而是使用与Class一样的泛型
     * @param <T>
     */
    public <T> void putValue(Class<T> type,T instance){
        if(type == null){
            throw new NullPointerException("Type is null");
        }
        heterMap.put(type,type.cast(instance));//也是为了确保value与key的类型一致
    }

    public <T> T getValue(Class<T> type){
        return type.cast(heterMap.get(type));
    }

    public static void main(String[] args) {
        HeterogeneousMap map = new HeterogeneousMap();
        map.putValue(String.class,"Java");
        map.putValue(Integer.class,1024);
        map.putValue(Class.class,HeterogeneousMap.class);

        String string = map.getValue(String.class);
        Integer integer = map.getValue(Integer.class);
        Class<?> clazz = map.getValue(Class.class);

        System.out.printf("%s %x %s%n",string,integer,clazz.getName());
        /**
         * out:Java 400 com.wl.study.collection.HeterogeneousMap
         */
    }
}
