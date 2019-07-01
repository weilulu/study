package com.wl.study.test;

import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import javax.tools.*;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date: 2019/6/27 18:17
 * @Description: 这种情形下修改了groovy脚本内容，是可以动态更新的
 */
public class GroovyScriptEngineTest {

    public static void main(String[] args) throws Exception{
        String filePath = "spring/src/main/resources/script";
        GroovyScriptEngine scriptEngine = new GroovyScriptEngine(filePath);
        Binding binding = new Binding();
        binding.setVariable("name","groovy");
        while (true){
            Object result = scriptEngine.run("hello.groovy",binding);
            TimeUnit.SECONDS.sleep(1);
        }

    }


    /**
     * jdk1.6新加入的功能：运行时编译java文件
     */
    public void test(){
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);
        JavaFileObject fileObject = new SimpleJavaFileObject(URI.create("string:///CalculatorTest"+JavaFileObject.Kind.SOURCE.extension),
                JavaFileObject.Kind.SOURCE){
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors)throws IOException{
                return "class CalculatorTest {}";
            }
        };
        JavaCompiler.CompilationTask task =
                compiler.getTask(null,standardFileManager,null,null,null, Arrays.asList(fileObject));
        task.call();
    }
}
