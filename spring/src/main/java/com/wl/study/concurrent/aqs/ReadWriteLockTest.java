package com.wl.study.concurrent.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.readLock();
    private volatile boolean updateFlag;

    public void test(){
        readLock.lock();
        if(!updateFlag){
            readLock.unlock();//一定要先释放了读锁再去获取写锁，如果直接获取写锁，当前线程会被阻塞
            writeLock.lock();//获取写锁
            try {
                if(!updateFlag){
                    //修改数据逻辑
                    updateFlag = true;
                }
                readLock.lock();//获取读锁
            }finally {
                writeLock.unlock();//释放写锁。到这里，锁降级完成
            }
        }
        try {
            //读取数据逻辑
        }finally {
            readLock.unlock();
        }
    }

}
