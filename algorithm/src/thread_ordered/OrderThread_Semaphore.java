package thread_ordered;

import java.util.concurrent.Semaphore;

public class OrderThread_Semaphore {
    public void first() { System.out.println("first"); }
    public void second() { System.out.println("second"); }
    public void third() { System.out.println("third"); }
    public static void main(String[] args){
        Semaphore sepmaphor1 = new Semaphore(0);
        Semaphore sepmaphor2 = new Semaphore(0);
        Semaphore sepmaphor3 = new Semaphore(0);
        OrderThread_Semaphore foo = new OrderThread_Semaphore();
        new Thread(() -> {
            foo.first();
            sepmaphor1.release();
        }).start();
        new Thread(() -> {
            try {
                sepmaphor1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.second();
            sepmaphor2.release();
        }).start();
        new Thread(() -> {
            try {
                sepmaphor2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            foo.third();
            sepmaphor3.release();
        }).start();
    }
}
