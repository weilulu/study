package com.wl.study.concurrent.cache;

/**
 * @Author:weilu
 * @Date:2020/1/24 22:29
 * @Description: 存入map里的键值对
 */
public class Pair<K,V> {
    public K key;
    public V value;
    public Pair(){}
    public Pair(K key,V value){
        this.key = key;
        this.value = value;
    }
}
