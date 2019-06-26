package com.groovy.study.intergration;

import groovy.lang.GroovyClassLoader;

/**
 * @Author:weilu
 * @Date: 2019/6/26 14:37
 * @Description: 集成groovy
 */
public class GroovyClassLoaderTest {

    public static void main(String[] args) throws Exception{
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        String script = "class Hello{void hello(){println 'hello'}}";
        Class clazz = groovyClassLoader.parseClass(script);
        clazz.getMethod("hello").invoke(clazz.newInstance());
    }
}
