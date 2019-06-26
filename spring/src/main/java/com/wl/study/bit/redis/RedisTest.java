package com.wl.study.bit.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:weilu
 * @Date: 2019/4/10 13:35
 */
public class RedisTest {

    private static final String KEYPRE = "recording:";
    private static final String RECORDINGFLAG = "1";//已打卡标识

    private Jedis jedis;

    @Before
    public void setup(){
        jedis = new Jedis("127.0.0.1",6379);
    }
    @Test
    public void testRedis(){
        System.out.println(jedis.get("test"));

    }
    @Test
    public void haha(){
        System.out.println(4/7);
        System.out.println(4%7);
        System.out.println(2/7);
        System.out.println(2%7);
    }
    @Test
    public void testBitSet(){
        String key = "online";
        Boolean setResult = jedis.setbit(key,1,"1");
        System.out.println("result:"+setResult);
        Boolean getResult = jedis.getbit(key,1);
        System.out.println("getResult:"+getResult);

        BitSet bitSet = BitSet.valueOf(jedis.get(key.getBytes()));
        System.out.println(bitSet.cardinality());
    }

    @Test
    public void testGet(){//TODO 想着取出key对应的value，但解析不了
        String key = KEYPRE + getYesterdayString();
        String value = jedis.get(key);
        System.out.println(value);
        System.out.println(jedis.get(key));

    }
    /**
     * 按天的维度，记录不同用户的打卡记录
     */
    @Test
    public void recordTest()throws Exception{
        Long[] userIds = {1001L,1003L};//,1120L
        for(Long userId : userIds){
            recording(getOffset(userId));
        }
    }

    /**
     * 统计当天打卡记录
     */
    @Test
    public void uniqueCountTest(){

        int count = uniqueCount(KEYPRE,getYesterdayString());
        System.out.println("count:"+count);
    }

    /**
     * 统计一段时间内打卡记录
     */
    @Test
    public void uniqueCount(){
        String today = getTodayString();
        String yesterday = getYesterdayString();
        int count = uniqueCount(KEYPRE,today,yesterday);
        System.out.println("total count:"+count);
    }
    /**
     * 对1000取模，计算用户ID的偏移量，这里有个问题需要注意：
     * 如果userId为1000001,1999999这两个值，BitMap里会出现大量的漏洞，
     * 因为这会占用bitMap里两端的值
     * @param userId
     * @return
     * @throws Exception
     */
    public Long getOffset(Long userId)throws Exception{
        if (userId == null){
            throw new Exception("userId is null");
        }
        return userId % 1000;
    }

    /**
     * 用BitMap记录打卡的数
     * @param offset
     */
    public void recording(Long offset){

        String date = getYesterdayString();

        Boolean result = jedis.setbit(KEYPRE+date,offset,RECORDINGFLAG);
        System.out.println(result);
    }

    /**
     * 取今天时间
     * @return
     */
    public String getTodayString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 取昨天时间
     */
    public String getYesterdayString(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return df.format(date);
    }
    /**
     * 算出一天的活跃用户数量
     * @param action
     * @param date
     * @return
     */
    public int uniqueCount(String action,String date){
        String key = action+date;
        BitSet users = BitSet.valueOf(jedis.get(key.getBytes()));
        if(users != null){
            return users.cardinality();
        }
        return 0;
    }

    /**
     * 计算某几个月内活跃用户的数量，取并集
     * @param action
     * @param dates
     * @return
     */
    public int uniqueCount(String action,String... dates){
        BitSet all = new BitSet();
        for(String date : dates){
            String key = action + date;
            BitSet users = BitSet.valueOf(jedis.get(key.getBytes()));
            all.or(users);
        }
        return all.cardinality();
    }
}
