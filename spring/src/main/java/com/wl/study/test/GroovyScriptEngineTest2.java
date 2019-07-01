package com.wl.study.test;

import groovy.lang.Binding;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date: 2019/6/27 18:17
 * @Description: 这种情形下修改了groovy脚本内容，是可以动态更新的
 * 测试获取返回值,可以获取到返回值，但其实这还是使用了GroovyClassLoader
 * 所以会在GroovyClassLoader相关例子上使用多线程
 */
public class GroovyScriptEngineTest2 {

    public static void main(String[] args) throws Exception{
        String filePath = "spring/src/main/resources/script/";
        String fileName = "engineTest2.groovy";
        String methodName = "getReturn";
        File file = new File(filePath+fileName);
        for(;;){
        GroovyScriptEngine scriptEngine = new GroovyScriptEngine(filePath);

        //Class clazz = scriptEngine.getGroovyClassLoader().parseClass(file);
        GroovyCodeSource codeSource = new GroovyCodeSource(file);
        //相比上一种使用了cache,如果脚本没变，则不会多次编译
        Class clazz = scriptEngine.getGroovyClassLoader().parseClass(codeSource,false);
        if(clazz != null){
            GroovyObject groovyObject = (GroovyObject)clazz.newInstance();

            Object o = groovyObject.invokeMethod(methodName, null);
            System.out.println(o);
            TimeUnit.SECONDS.sleep(1);

        }
        }
    }

}
