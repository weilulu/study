package com.wl.study.watcher;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author:weilu
 * @Date: 2019/3/15 21:33
 * 观察点，每个观察点有自己的子节点，形成一颗观察树
 */
public class MonitNode {

    private String alias;
    /**子节点**/
    private Cache<String,MonitNode> children;
    /**请求历史，一分钟粒度，最长一小时**/
    private Map<Long,LongAdder> history = new ConcurrentHashMap<Long, LongAdder>();

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Cache<String, MonitNode> getChildren() {
        return children;
    }

    public void setChildren(Cache<String, MonitNode> children) {
        this.children = children;
    }
    public Cache<String, MonitNode> getChildren(int topSize) {
        if(children == null){
            //规定缓存项的数目不超过固定值,这里是topSize
            children = CacheBuilder.newBuilder().maximumSize(topSize).build();
        }
        return children;
    }

    public Map<Long, LongAdder> getHistory() {
        return history;
    }

    public void setHistory(Map<Long, LongAdder> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "MonitNode{" +
                "alias='" + alias + '\'' +
                ", children=" + children +
                ", history=" + history +
                '}';
    }
}
