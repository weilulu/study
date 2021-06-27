package main;

public interface RetryPolicy {

    /**
     *
     * @param retryCount
     * @param elapsedTimeMs 已过去的时间
     * @param sleeper use this to sleep
     */
    boolean allowRetry(int retryCount, long elapsedTimeMs, RetrySleeper sleeper);

    default boolean allowRetry(Throwable exception){
        /**
         * if(exception instanceof selfDefinitionException){
         *      other express...
         *      return true;
         * }
         */
        return false;
    }
}
