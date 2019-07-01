package com.wl.study.test;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date: 2019/6/27 18:17
 * @Description: 这种情形下修改了groovy脚本内容，是可以动态更新的
 */
public class MultiThreadGroovyScriptEngineTest {

    private CountDownLatch latch = new CountDownLatch(1);
    public static final String FILE_PATH="spring/src/main/resources/script";
    public static void main(String[] args) throws Exception{

        MultiThreadGroovyScriptEngineTest test = new MultiThreadGroovyScriptEngineTest();
        for(int i=0;i<500;i++){
            Thread t = new Thread(()->{
                test.excute();
            });
            t.start();
            //Thread.sleep(2000L);
        }

    }

    public void excute(){
        GroovyScriptEngine scriptEngine = null;
        Object result = null;
        try {
            scriptEngine = new GroovyScriptEngine(FILE_PATH);
            Binding binding = new Binding();
            binding.setVariable("name","groovy");
            result = scriptEngine.run("hello.groovy",binding);
            System.out.println(result);
        }catch (IOException | ResourceException|ScriptException  exception){
            exception.printStackTrace();
        }
    }

}
