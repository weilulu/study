package com.wl.study.bean;

import org.junit.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

import java.beans.PropertyDescriptor;

/**
 * @Author:weilu
 * @Date: 2019/4/15 20:54
 *
 * bean的一些操作工具
 * https://juejin.im/post/5a77f3eb6fb9a06361085451
 * 操作bean的工具类：
 * Apache的BeanUtils和PropertyUtils
 * cglib的BeanMap和BeanCopier
 * spring的BeanUtils
 *
 *
 *
 * Spring中BeanWrapper 的主要功能在于：

    1.支持设置嵌套属性
    2.支持属性值的类型转换（设置ConversionService）
    3.提供分析和操作标准JavaBean的操作：获取和设置属性值（单独或批量），获取属性描述符以及查询属性的可读性/可写性的能力。

 */
public class BeanWrapperTest {

    @Test
    public void setBeanTest(){
        User user = new User();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
        //方式一
        bw.setPropertyValue("userName","susan");
        //方式二
        PropertyValue pv = new PropertyValue("userName","susan");
        bw.setPropertyValue(pv);
        System.out.println(user.getUserName());

    }

    class User{
        private String userName;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
