package thread_ordered;

import java.util.concurrent.CountDownLatch;

public class OrderThread_CountdownLatch {
    public void first() { System.out.println("first"); }
    public void second() { System.out.println("second"); }
    public void third() { System.out.println("third"); }
    public static void main(String[] args){
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        OrderThread_CountdownLatch foo = new OrderThread_CountdownLatch();
        new Thread(() -> {
            foo.first();
            latch1.countDown();
        }).start();
        new Thread(() -> {
            try {
                latch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.second();
            latch2.countDown();
        }).start();
        new Thread(() -> {
            try {
                latch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.third();
        }).start();
    }
}
