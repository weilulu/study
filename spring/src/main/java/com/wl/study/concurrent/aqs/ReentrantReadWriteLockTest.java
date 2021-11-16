package com.wl.study.concurrent.aqs;


import java.math.BigInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    public static void main(String[] args) {

        //System.out.println(Integer.toHexString(65535));//对应十六进制里的0x0000FFFF
        //System.out.println(String.format("%08X",65535));
        readWriteLock.readLock().lock();
        System.out.println("读锁获取成功");
        readWriteLock.writeLock().lock();
        System.out.println("写锁获取成功");
        readWriteLock.readLock().unlock();
        System.out.println("读锁释放成功");
        readWriteLock.writeLock().unlock();
        System.out.println("写锁释放成功");
    }
}
