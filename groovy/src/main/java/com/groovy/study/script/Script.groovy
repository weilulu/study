package com.groovy.study.script

/**
 * @Author:weilu
 * @Date:2019 / 8 / 2 7 21:54
 * @Description:$ { D e s c r i p t i o n }
 */
/*class Script {
    private Map map = new HashMap();
    def getMap(){
        map.put('busi_seq', UUID.randomUUID().toString().replace('-','')); map.put('channel_id','OPNSE'); map.put('initiator_date', new Date().parse('yyyymmdd',Calendar.instance.toString())); int random = getRandom(); String initiatorSeq = 'OPNSE'+new Date().parse('yyyyMMddHHmmssSSS',Calendar.instance.toString())+random; map.put('initiator_seq',initiatorSeq); map.put('initiator_system','OPNSE'); map.put('initiator_time',new Date().parse('yyyyMMdd HH:mm:ss SSS',Calendar.instance.toString()));map.put('sponsor_system','OPNSE');
        return map;
    }
    def getRandom(){int digit = (int)Math.pow(10,7-1);int rs = new Random().nextInt(digit * 10);if(rs < digit){rs += digit;};return rs;}
}*/

class Script{Map map = new HashMap();def getMap(){map.put('busi_seq', UUID.randomUUID().toString().replace('-',''));map.put('channel_id','OPNSE');map.put('initiator_date', new Date().format('yyyymmdd'));int random = getRandom(); String initiatorSeq = 'OPNSE'+new Date().format('yyyyMMddHHmmssSSS')+random;map.put('initiator_seq',initiatorSeq);map.put('initiator_system','OPNSE');map.put('initiator_time',new Date().format('yyyyMMdd HH:mm:ss SSS',));map.put('sponsor_system','OPNSE');return map; };def getRandom(){int digit = (int)Math.pow(10,7-1);int rs = new Random().nextInt(digit * 10);if(rs < digit){rs += digit;};return rs;}}
