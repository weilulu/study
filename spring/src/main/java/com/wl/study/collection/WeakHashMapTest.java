package com.wl.study.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

/**
 * @Author:weilu
 * @Date: 2019/4/2 19:13
 * 如果一个对象只有一个弱引用，那么垃圾回收器可以随时回收该对象的内在，它不需要等到系统内在存不足时才回收，
 *
 */
public class WeakHashMapTest {

    @Test
    public void test(){

        Map<Key,Project> map = new WeakHashMap<>();//如果下面调用了gc()，则map里只会存在key2所在的键值对
        //Map<Key,Project> map = new HashMap<>();//如果下面调用了gc()，map里还是会有两个元素
        Key key1 = new Key("ACTIVE");
        Key key2 = new Key("INACTIVE");
        Project project1 = new Project(100,"Customer Management System");
        map.put(key1,project1);
        map.put(key2,new Project(200,"Employee Management System"));

        key1 = null;
        //project1 = null;//将map中的value置为null，map里还有会有两个元素；而将key置为null，则只有一个
        System.gc();//这个会触发Full GC ，尽量不要使用
        for (Map.Entry<Key,Project> entry : map.entrySet()){
            System.out.println(entry.getKey().getKey()+","+entry.getValue().getId());
        }
    }

    class Key{
        private String key;
        public Key(String key){
            super();
            this.key = key;
        }
        public String getKey(){
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
    class Project{
        private Integer id;
        private String name;

        public Project(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void threadLocalTest(){
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("test");
        System.out.println(threadLocal.get());
        threadLocal.set("test2");
        System.out.println(threadLocal.get());

    }
}
