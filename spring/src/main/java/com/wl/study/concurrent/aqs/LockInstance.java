package com.wl.study.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author:weilu
 * @Date: 2019/3/7 23:04
 */
public class LockInstance implements Lock {

    private final Sync sync = new Sync(2);

    public LockInstance() throws IllegalAccessException {
    }

    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int state) throws IllegalAccessException {
            if(state <= 0){
                throw new IllegalAccessException("count must large than 0");
            }
            setState(state);
        }

        @Override
        public int tryAcquireShared(int arg){
            for(;;){
                System.out.println("try acquire...");
                int current = getState();
                int now = current - arg;
                if(now < 0 || compareAndSetState(current,now)){
                    return now;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int arg){
            for(;;){
                System.out.println("try release...");
                int current = getState();
                int now = current + arg;
                if(compareAndSetState(current,now)){
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {

        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
