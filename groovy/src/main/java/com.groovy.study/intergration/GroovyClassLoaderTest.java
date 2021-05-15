package com.groovy.study.intergration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.util.GroovyScriptEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author:weilu
 * @Date: 2019/6/26 14:37
 * @Description: 集成groovy
 */
public class GroovyClassLoaderTest{

    /*public static void main(String[] args) throws Exception{
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        String script = "class Hello{void hello(){println 'hello'}}";
        Class clazz = groovyClassLoader.parseClass(script);
        clazz.getMethod("hello").invoke(clazz.newInstance());
    }*/


    /**
     * 这个是为了拿一个动态map，map里的key对应的value是变动的，比如有时间
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        String scriptMap = getScriptMap();
        Class clazz = groovyClassLoader.parseClass(scriptMap);
        Object result = clazz.getMethod("getMap").invoke(clazz.newInstance());
        System.out.println(result);

        //String test = "{\"sss\":\"111\",\"aaaa\":{\"merOrderNo\":\"222\",\"merOrderNo2\":\"222\"},\"bbb\":{\"merOrderNo\":\"333\",\"merOrderNo2\":\"333\"}}";
        String test = "{\"rpcRequest\":{\"header\":{},\"commonData\":{},\"body\":{}}}";
        //JSONObject.parseObject(test,)
        JSONObject jsonObject = JSONObject.parseObject(test);
        jsonObject = recursiveJsonObject(jsonObject,result);
        System.out.println(jsonObject.toJSONString());
    }

    private static JSONObject recursiveJsonObject(JSONObject jsonObject,Object scriptValue){
        String testKey = "commonData";
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for(Map.Entry<String,Object> entry :entries){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(testKey.equalsIgnoreCase(key)){
            }/*else{
                if (value != null && StringUtils.isNotBlank(String.valueOf(value))){
                    recursiveJsonObject(JSONObject.parseObject(String.valueOf(value)),scriptValue);
                }
            }*/
        }
        return jsonObject;
    }
    public static String getScriptMap(){
        String script = "class Script {\n" +
                "    Map map = new HashMap();\n" +
                "    def getMap(){\n" +
                "        map.put('busi_seq', UUID.randomUUID().toString().replace('-','')); map.put('channel_id','OPNSE'); map.put('initiator_date', new Date().format('yyyymmdd')); int random = getRandom(); String initiatorSeq = 'OPNSE'+new Date().format('yyyyMMddHHmmssSSS')+random; map.put('initiator_seq',initiatorSeq); map.put('initiator_system','OPNSE'); map.put('initiator_time',new Date().format('yyyyMMdd HH:mm:ss SSS',));map.put('sponsor_system','OPNSE');\n" +
                "        return map;\n" +
                "    }\n" +
                "    def getRandom(){int digit = (int)Math.pow(10,7-1);int rs = new Random().nextInt(digit * 10);if(rs < digit){rs += digit;};return rs;}\n" +
                "}";
        return script;
    }
}



