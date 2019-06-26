package com.groovy.study.intergration;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.util.concurrent.TimeUnit;

//import javax.naming.Binding;

/**
 * @Author:weilu
 * @Date: 2019/6/26 14:49
 * @Description: 动态执行groovy，脚本没找到
 */
public class GroovyScriptEnginTest {

    public static void main(String[] args) throws Exception{
        File currentDir = new File(".");
        GroovyScriptEngine scriptEngin =
                new GroovyScriptEngine(currentDir.getAbsolutePath());
        Binding binding = new Binding();
        binding.setVariable("name","groovy");
        while(true){
            scriptEngin.run("hello.groovy",binding);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
