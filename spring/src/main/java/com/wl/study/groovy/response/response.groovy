package com.wl.study.groovy.response

import com.wl.study.groovy.service.ResponseService
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired

/**
 * @Author:weilu
 * @Date: 2019/6/26 19:32
 * @Description: ${Description}
 *
 */
class response {

    @Autowired
    private ResponseService responseService;

    def run(){
        responseService.test();

        def jsonStr = JsonOutput.toJson([gw_func_adde:'/mapi/template/1/test04', response:'RESPONSE']);
        return jsonStr;
    }
}
