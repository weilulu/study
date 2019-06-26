package com.wl.study.groovy.controller;

import com.wl.study.groovy.response.response;
import com.wl.study.groovy.util.ClassUtils;
import com.wl.study.groovy.util.SpringContextUtils;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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
        //response response = (response) SpringContextUtils.getBean("response");
        //response.run();
        Object result = groovyObject.invokeMethod("run",null);
        return result;
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
