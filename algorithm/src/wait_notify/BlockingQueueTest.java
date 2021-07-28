package wait_notify;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞队列实现生产消费模式
 */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        int numProducers = 4;
        int numConsumers = 3;
        BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>(20);
        for (int i = 0; i < numProducers; i++) {
            new Thread(new Producer(myQueue)).start();
        }
        for (int i = 0; i < numConsumers; i++) {
            new Thread(new Consumer(myQueue)).start();
        }
        Thread.sleep(10 * 1000);
        System.exit(0);
    }
}

class Producer implements Runnable{
    private BlockingQueue<Object> blockingQueue;
    Producer(BlockingQueue<Object> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        while (true){
            Object message = getMessage();
            blockingQueue.add(message);
            System.out.println("生产了消息,消息队列大小: "+ blockingQueue.size());
        }
    }
    private Object getMessage(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            //
        }
        return new Object();
    }
}

class Consumer implements Runnable{
    private BlockingQueue<Object> blockingQueue;
    Consumer(BlockingQueue<Object> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        try {
            while (true){
                Object message = blockingQueue.take();
                System.out.println("消费了消息，消息队列大小: "
                        + blockingQueue.size());
                take(message);
            }
        }catch (InterruptedException e){

        }
    }
    void take(Object obj) {
        try {
            Thread.sleep(100); // simulate time passing
        } catch (InterruptedException ex) {
            System.out.println("Consumer Read INTERRUPTED");
        }
        System.out.println("Consuming object " + obj);
    }
}
