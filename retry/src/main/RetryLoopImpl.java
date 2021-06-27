package main;

/**
 * 对RetryLoop的实现，里面有RetryPolicy、RetrySleeper两个重要的属性
 * RetryPolicy类里有通过对异常、重试次数来判断是否再进行重试
 * RetrySleeper线程休眠工具
 */
public class RetryLoopImpl extends RetryLoop {

    private boolean isDone = false;
    private int retryCount = 0;
    private final long startTimeMs = System.currentTimeMillis();
    private final RetryPolicy retryPolicy;
    private static final RetrySleeper sleeper = (time, unit) -> unit.sleep(time);

    RetryLoopImpl(RetryPolicy retryPolicy){
        this.retryPolicy = retryPolicy;
    }

    static RetrySleeper getRetrySleeper(){
        return sleeper;
    }

    @Override
    public boolean shouldContinue() {
        return !isDone;
    }

    @Override
    public void markComplete() {
        isDone = true;
    }

    /**
     * 异常抛出后流程会中断
     * @param exception
     * @throws Exception
     */
    @Override
    public void takeException(Exception exception) throws Exception {
        boolean rethrow =true;
        if(retryPolicy.allowRetry(exception)){//有的异常发生后是可以重试的
            //在sleep一定时间内如果未被中断也可以重试
            if(retryPolicy.allowRetry(retryCount++,System.currentTimeMillis() - startTimeMs,sleeper)){
                rethrow = false;
            }
        }
        //如果上面两个条件都不满足，则抛出异常结束流程
        if(rethrow){
            throw exception;
        }
    }
}
