package com.wl.study.spring_extent.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author:weilu
 * @Date: 2019/1/14 22:37
 */
@Component
public class User {
    @Value("老名字")
    private String name;
    @Value("30")
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
