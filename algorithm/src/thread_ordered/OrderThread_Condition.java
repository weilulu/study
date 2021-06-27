package thread_ordered;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class OrderThread_Condition{
    boolean flag = true;
    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public Runnable foo(){
        return  new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for(int i=0;i<5;i++){
                        if(!flag){
                            c1.await();
                        }
                        flag = false;
                        System.out.println("foo");
                        c2.signal();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };
    }
    public Runnable bar(){
        return new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    for(int i=0;i<5;i++){
                        if(flag){
                            c2.await();
                        }
                        flag = true;
                        System.out.println("bar");
                        c1.signal();
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };
    }

    public static void main(String[] args) {
        OrderThread_Condition condition = new OrderThread_Condition();
        new Thread(condition.foo()).start();
        new Thread(condition.bar()).start();

/**
 *输出　foo/bar/foo/bar...
 *
 */
    }
}