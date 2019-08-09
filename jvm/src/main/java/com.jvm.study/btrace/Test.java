package com.jvm.study.btrace;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import static org.aspectj.apache.bcel.util.ClassPath.getClassPath;

/**
 * @Author:weilu
 * @Date: 2019/7/1 17:08
 * @Description: ${Description}
 */
public class Test{

    public static void main(String[] args) throws Exception{
        System.out.println(getClassPath());
        Test test = new Test();
        test.getPath();

        test.getProperties();
    }

    public void getPath(){
        String file = this.getClass().getResource("/").getFile();
        System.out.println(file);
    }

    public void getProperties()throws Exception{
        Properties pro = new Properties();
        InputStream in = ClassLoader.getSystemResourceAsStream("application.properties");
        pro.load(in); ///加载属性列表
        Iterator<String> it = pro.stringPropertyNames().iterator();
        while(it.hasNext()){
            String key=it.next();
            System.out.println(key+":"+pro.getProperty(key));
        }
        in.close();

    }
}
