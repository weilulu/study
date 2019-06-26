package com.wl.study.watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:weilu
 * @Date: 2019/3/15 21:27
 * 请求点，用于描述监控的请求所解析出的结构化数据
 * 将请求打到的点构建成树形，以便计数器统计
 */
public class RequestNode {
    private String alias;
    private String key;
    private List<RequestNode> children = new ArrayList<RequestNode>();
    private int topSize;

    public RequestNode(String alias, String key, int topSize) {
        this.alias = alias;
        this.key = key;
        this.topSize = topSize;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<RequestNode> getChildren() {
        return children;
    }

    public void setChildren(List<RequestNode> children) {
        this.children = children;
    }

    public int getTopSize() {
        return topSize;
    }

    public void setTopSize(int topSize) {
        this.topSize = topSize;
    }


    @Override
    public String toString() {
        return "RequestNode{" +
                "alias='" + alias + '\'' +
                ", key='" + key + '\'' +
                ", children=" + children +
                ", topSize=" + topSize +
                '}';
    }
}
