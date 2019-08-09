package script

import groovy.json.JsonOutput


    def jsonStr = JsonOutput.toJson([gw_func_adde:"/mapi/template/1/test04",response:"RESPONSE"]);
    if(param == "test"){
        return jsonStr
    }else{
        return "error"
    }