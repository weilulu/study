package main.policy;

public class RetryOneTime extends RetryNTimes{
    public RetryOneTime(int sleepMsBetweenRetries) {
        super(1, sleepMsBetweenRetries);
    }
}
