package com.wl.study.collection;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class IteratorRemoveTest {

    @Test
    public void test() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        for (Integer i : list) {
            System.out.println(i);
            list.remove(i);//出现ConcurrentModificationException异常
        }
    }

    @Test
    public void test2() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(list.size());
    }

}
