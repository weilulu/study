package com.wl.study.groovy.response;

import com.wl.study.groovy.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:weilu
 * @Date: 2019/7/1 16:04
 * @Description: ${Description}
 */
public class TestService {
    @Autowired
    private ResponseService responseService;

    public void  response(){
        responseService.test();
        System.out.println("test...");
    }
}
