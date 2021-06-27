package com.wl.study.concurrent.thread_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ExceptionInChildThread implements Runnable{
    private static Logger logger = LoggerFactory.getLogger(ExceptionInChildThread.class);

    @Override
    public void run() {
        /*try {
            throw new RuntimeException("子线程发生了异常...");
        } catch (Exception e) {
            logger.error("在线程内部捕获异常", e);
        }*/
        System.out.println("啥也没做");
        throw new RuntimeException("子线程发生了异常...");
    }
    /**
     * 模拟子线程发生异常
     *
     * @throws InterruptedException
     */
    private static void exceptionThread() throws InterruptedException {
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
        new Thread(new ExceptionInChildThread()).start();
        TimeUnit.MILLISECONDS.sleep(200L);
    }

    /**
     * 在主线程尝试通过try catch捕获异常
     */
    private static void catchInMain() {
        try {
            exceptionThread();
        } catch (Exception e) {
            //无法捕获发生在其他线程中的异常
            logger.error("捕获到了异常?", e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExceptionInChildThread.catchInMain();
    }
}
