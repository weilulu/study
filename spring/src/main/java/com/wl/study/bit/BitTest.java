package com.wl.study.bit;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.BitSet;

/**
 * @Author:weilu
 * @Date: 2019/4/12 22:04
 */
public class BitTest {

    @Test
    public void test(){
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
    }
    @Test
    public void test1(){
        System.out.println(2 & (1<<2));//说明2的第3位不是1
        System.out.println(2 & (1<<1));//说明2的第2位是1

        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(2 ^ 6));
        System.out.println(2 ^ 6);//异或操作，相同为0,不同为1
    }

    /**
     * 判断122的第3位是不是1
     * 判断一个数的第n位是不是1,只需要判断x & (1 << n)是否不为0，若不为则是1，否则是0
     */
    @Test
    public void test2(){
        int result = 122 & (1 << 3);
        if(result != 0){
            System.out.println(Integer.toBinaryString(122)+",第3位是1");
        }else{
            System.out.println("122第3位不是1");
        }
    }

    /**
     * 将一个数的指定位数设为1
     * x | (1 << n)
     */
    @Test
    public void test3(){
        System.out.println(Integer.toBinaryString(122));//1111010
        int result = 122 | (1 << 2);
        System.out.println(Integer.toBinaryString(result));//1111110
        System.out.println(result);//126
    }


    /**
     * 将一个数的指定位数设为0
     * x & ~(1 << n)
     */
    @Test
    public void test4(){
        System.out.println(Integer.toBinaryString(122));//1111010
        int result = 122 & ~(1 << 1);
        System.out.println(Integer.toBinaryString(result));//1111000
        System.out.println(result);//120
    }

    /**
     * 将第n位取反
     */
    @Test
    public void test5(){
        System.out.println(Integer.toBinaryString(122));
        int result = 122 ^ (1 << 2);
        System.out.println(Integer.toBinaryString(result));
        System.out.println(result);
    }

    /**
     * java.util已经封装了操作bit的工具
     * BitSet是位操作的对象，值只有0或1即false和true，内部维护了一个long数组，初始只有一个long，所以BitSet最小的size是64
     * BitSet内部会动态扩充
     */
    @Test
    public void testBitSet(){
        BitSet bitSet = new BitSet();
        bitSet.set(2);
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(2));
    }

    public static void main(String[] args) throws Exception{
        String test = "20690101";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
        String format = sf.format(sf2.parse(test));
        System.out.println(format);
    }
}
