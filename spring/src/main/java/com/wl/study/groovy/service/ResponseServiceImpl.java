package com.wl.study.groovy.service;

import org.springframework.stereotype.Service;

/**
 * @Author:weilu
 * @Date: 2019/6/26 18:01
 * @Description: ${Description}
 */
@Service
public class ResponseServiceImpl implements ResponseService{

    @Override
    public void test(){
        System.out.println("service...");
    }
}
