package threadLocal;

import org.apache.curator.utils.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    /*private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return nextId.getAndIncrement();
        }
    };*/
    private static final ThreadLocal<String> tl = new ThreadLocal<>();
    //或者这样也行
    //private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());
    /*public static int getNextId(){
        return threadId.get();
    }*/

    public static void main(String[] args) {
        //int nextId = getNextId();
        //System.out.println("nextId:"+nextId);
        tl.set("test");
        /*System.out.println(tl.get());
        tl.set("test2");
        System.out.println(tl.get());*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                tl.set("A++++");
                System.out.println(tl.get());
            }
        },"线程1").start();
        new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tl.set("B++++");
                        System.out.println(tl.get());
                    }
                },"线程2").start();


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tl.get());
    }

}
