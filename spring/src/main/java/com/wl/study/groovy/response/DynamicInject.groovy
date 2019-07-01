package com.wl.study.groovy.response

import com.wl.study.groovy.service.ResponseService
import org.springframework.beans.factory.annotation.Autowired

class DynamicInject {

    @Autowired
    private ResponseService responseService;

    def response(){
        responseService.test()
        println "response...."
    }
}