package wait_notify;

import java.util.concurrent.TimeUnit;

/**
 * 等待通知模式
 * 等待方：
 * １）获取对象的锁
 * ２）如果条件不满足，那么调用对象的wait方法，被通知后仍要检查条件
 * ３）条件满足则执行对应逻辑
 * 通知方：
 * １）获取对象的锁
 * ２）改变条件
 * ３）通知所有等待在对象上的线程
 */
public class WaitNotify {
    private static boolean flag = false;
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread wait = new Thread(new Wait(),"WAIT THREAD");
        wait.start();
        Thread notify = new Thread(new Notify(),"NOTIFY THREAD");
        notify.start();
    }

    static class Wait implements Runnable{
        public void run(){
            synchronized (lock){
                while (!flag){
                    try {
                        System.out.println(Thread.currentThread()+" flag is false,wait...");
                        lock.wait();//会释放锁，不然notify获取不到锁
                    }catch (InterruptedException e){
                        //
                    }
                }
                System.out.println(Thread.currentThread()+" flag is true,running...");
            }
        }
    }

    static class Notify implements Runnable{
        public void run(){
            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock,notify...");
                flag = true;
                lock.notify();
                try {
                    //wait的时候会释放锁对象，sleep的时候不会释放锁对象
                    //所以上面将flag设置了true，wait线程仍要等待5s后才会执行
                    TimeUnit.SECONDS.sleep(5);
                }catch (InterruptedException e){
                    //
                }
            }
        }
    }
}
