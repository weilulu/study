package script

import com.wl.study.groovy.service.ResponseServiceImpl
import groovy.json.JsonOutput
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired

/**
 * @Author:weilu
 * @Date: 2019/6/26 17:53
 * @Description: ${Description}
 *
 */
@CompileStatic
class Response {

    @Autowired
    private ResponseServiceImpl responseService;

    def run(){
        //responseService.test();

        def jsonStr = JsonOutput.toJson([gw_func_adde:'/mapi/template/1/test04', response:'RESPONSE2']);
        return jsonStr;
    }

}
