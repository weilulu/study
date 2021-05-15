package com.data.struct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:weilu
 * @Date:2019/8/30 10:06
 * @Description:${Description}
 */
public class JsonObjectTree {

    public <K,V> void putNode(K key,V value){

    }
    class Node<T>{
        private Node<T> parentNode;
        private Node<T> childNode;
        private Node<T> siblingNode;
        private Boolean hasNextNode;
        private List<Node<T>> nodeList;

    }
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
    }
}
