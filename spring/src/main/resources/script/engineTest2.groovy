import groovy.json.JsonOutput

//class engineTest2{

def getReturn(){
    def jsonStr = JsonOutput.toJson([gw_func_adde:'/mapi/template/1/test04', response:'RESPONSE']);
    return jsonStr;
  }
//}