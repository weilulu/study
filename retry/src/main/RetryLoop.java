package main;

import java.util.concurrent.Callable;

/**
 *有任务需要执行时，并不是直接去执行而是使用RetryLoop里的callWithRetry方法封装后再执行，
 * 这样，如果在执行的过程中发生异常，那么就可以根据异常的种类判断是继续重试还是抛出异常
 */
public abstract class RetryLoop {

    public static <T> T callWithRetry(RetryLoop retryLoop, Callable<T> proc)throws Exception{
        T result = null;
        while (retryLoop.shouldContinue()){
            try {
                result = proc.call();
                retryLoop.markComplete();
            }catch (Exception e){
                retryLoop.takeException(e);
            }
        }
        return result;
    }

    /**
     * 返回true需要继续执行
     * @return
     */
    public abstract boolean shouldContinue();

    /**
     * 任务执行成功后调用这个方法
     */
    public abstract void markComplete();

    /**
     * pass any caught exception here，
     * 对异常进行过滤判断，从而进行重试或抛出异常中断流程
     * @param exception
     */
    public abstract void takeException(Exception exception) throws Exception;

}
