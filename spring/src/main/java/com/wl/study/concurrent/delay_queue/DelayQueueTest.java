package com.wl.study.concurrent.delay_queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/3/17 14:20
 * @Description: 简单的延迟队列例子
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        long now = System.currentTimeMillis();
        new Thread(()->{
            while (true){
                try {
                    //取出元素
                    System.out.println(delayQueue.take().deadLine - now);
                    /**
                     * 输出为：1000,2000,5000,7000,8000
                     * 先到期的先输出
                     */
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
        delayQueue.add(new DelayQueueTest().new Message(now + 5000));
        delayQueue.add(new DelayQueueTest().new Message(now + 8000));
        delayQueue.add(new DelayQueueTest().new Message(now + 2000));
        delayQueue.add(new DelayQueueTest().new Message(now + 1000));
        delayQueue.add(new DelayQueueTest().new Message(now + 7000));
    }

    class Message implements Delayed{
        long deadLine;

        public Message(long deadLine){
            this.deadLine = deadLine;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return deadLine - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int)(getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString(){
            return String.valueOf(deadLine);
        }
    }
}
