package com.wl.study.groovy.controller;

import com.wl.study.groovy.response.DynamicInject;
import com.wl.study.groovy.util.ClassUtils;
import com.wl.study.groovy.util.SpringContextUtils;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;


/**
 * @Author:weilu
 * @Date: 2019/6/26 16:00
 * @Description: ${Description}
 */
@Controller
@RequestMapping("groovy")
public class GroovyResponseAction {

    /**
     * 无须参数
     * @return
     * @throws Exception
     */
    @RequestMapping("responseNoParam")
    @ResponseBody
    public Object responseNoParam()throws Exception{
        String script = getNoParamScript();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class clazz = groovyClassLoader.parseClass(script);
        GroovyObject groovyObject = (GroovyObject)clazz.newInstance();
        Object result = groovyObject.invokeMethod("getMap",null);
        return result;
    }


    /**
     * 外部传参
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping("responseWithParam")
    @ResponseBody
    public Object responseWithParam(String name)throws Exception{
        String script = getParamScript();
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class clazz = groovyClassLoader.parseClass(script);
        GroovyObject groovyObject = (GroovyObject)clazz.newInstance();
        Object result = groovyObject.invokeMethod("getMap",name);
        return result;
    }

    /**
     * 解析外部的groovy文件
     * @return
     * @throws Exception
     */
    @RequestMapping("parseScript")
    @ResponseBody
    public Object parseScript()throws Exception{
        Class<?> groovyClass = ClassUtils.getGroovyClass("script/test.groovy");
        GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
        Object result = groovyObject.invokeMethod("getJsonString",null);
        return result;
    }

    @ResponseBody
    @RequestMapping("response")
    public Object response()throws Exception{
        String fileName = "script/Response.groovy";
        Class<?> groovyClass = SpringContextUtils.getGroovyClass(fileName);
        GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();
        Object result = groovyObject.invokeMethod("run",null);
        return result;
    }

    /**
     * 修改了脚本内容后，会实时print新内容，也可以拿到返回值
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("dynamicResponse")
    public Object dynamicResponse()throws Exception{
        String filePath = "D:\\workspace\\study\\spring\\src\\main\\resources\\script";
        GroovyScriptEngine scriptEngine = new GroovyScriptEngine(filePath);
        Binding binding = new Binding();
        binding.setVariable("param","test");
        Object object1 = scriptEngine.run("dynamicResponse.groovy",binding);
        return object1;
    }

    /**
     * 修改了脚本内容后，会实时print新内容，也可以拿到返回值。。。
     * 有一点需要注意的是，如果脚本里定义的是一个方法，则是拿不到返回值的，scriptEngine.run这样执行也一样
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("dynamicResponseWithJDK6")
    public Object dynamicResponseWithJDK6()throws Exception{
        String filePath = "D:\\workspace\\study\\spring\\src\\main\\resources\\script";
        FileReader fileReader = new FileReader(filePath+"\\dynamicResponse.groovy");

        ScriptEngineManager manager = new ScriptEngineManager();

        ScriptEngine groovy = manager.getEngineByName("groovy");
        Object result = groovy.eval(fileReader);
        return result;
    }

    /**
     * 动态将bean注入到groovy脚本中，这样groovy也可以使用已有的bean
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("dynamicInjectBean")
    public void dynamicInjectBean()throws Exception{
        DynamicInject object = SpringContextUtils.getBean("dynamicInject",DynamicInject.class);
        object.response();
        System.out.println(object);
    }

    /**
     * 这个不行
     * @return
     */
    @ResponseBody
    @RequestMapping("useGroovyApplication")
    public Object useGroovyApplication(){
        GenericGroovyApplicationContext groovyApplicationContext =
                new GenericGroovyApplicationContext("script/engineTest2.groovy");
        Object object = groovyApplicationContext.invokeMethod("getReturn","1");
        return object;
    }
    /**
     * 无参脚本
     * @return
     */
    private String getNoParamScript(){
        String script = "def getMap(){def map = ['name':'Alex']; return map;}";
        return script;
    }

    /**
     * 有参脚本
     * @return
     */
    private String getParamScript(){
        String script = "def getMap(name){def map = ['name':name]; return map;}";
        return script;
    }
}
