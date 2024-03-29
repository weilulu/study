package com.wl.study.concurrent.thread_exception;

//import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池发生了异常：
 * https://mp.weixin.qq.com/s/kl84A4W1W2iTJNylbfouFQ
 */
//@Slf4j
public class ExceptionWithThreadPool {
    private static Logger log = LoggerFactory.getLogger(ExceptionWithThreadPool.class);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                1L,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(1024),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        //设置线程异常处理器
                        //thread.setUncaughtExceptionHandler(new CustomThreadUncaughtExceptionHandler());
                        return thread;
                    }
                }
        ) {
            /**
             * 捕获{@code FutureTask<?>}抛出的异常
             *
             * @param r
             * @param t
             */
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (r instanceof FutureTask<?>) {
                    try {
                        //get()的时候会将异常内的异常抛出
                        ((FutureTask<?>) r).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e) {
                        log.error("捕获到线程的异常返回值", e);
                    }
                }
                //Throwable t永远为null，拿不到异常信息
                //log.error("afterExecute中捕获到异常，", t);
            }
        };

        threadPoolExecutor.execute(new Runnable() {
                                       @Override
                                       public void run() {
                                           throw new RuntimeException("execute()发生异常");
                                       }
                                   }
        );
        TimeUnit.MILLISECONDS.sleep(200L);

        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("submit.run()发生异常");
            }
        });
        TimeUnit.MILLISECONDS.sleep(200L);

        threadPoolExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new RuntimeException("submit.call()发生异常");
            }
        }).get();   //get()的时候会将异常抛出

        threadPoolExecutor.shutdown();
    }
}
