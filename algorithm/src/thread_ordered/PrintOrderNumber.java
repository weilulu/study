package thread_ordered;

import java.util.concurrent.Semaphore;

/**
 * 要求：使用３个线程交替打印１－１００之间的数，如：
 * t1->1,t2->2,t3->3,t1->4,t2->5....
 *
 * 需要注意的地方：PrintNumber类里的number一定要使用static修饰
 */
public class PrintOrderNumber {
    private static Semaphore s1 = new Semaphore(0);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);
    public static void main(String[] args) {
        new PrintNumber(1,"t-A",s1,s2).start();
        new PrintNumber(2,"t-B",s2,s3).start();
        new PrintNumber(0,"t-C",s3,s1).start();
        /*new PrintNumber(1,"t-a",s1,s2).start();
        new PrintNumber(0,"t-b",s2,s1).start();*/
    }
}
class PrintNumber extends Thread{
    private volatile static int number=1;//这里一定要使用static修饰
    private int mod;
    private String threadName;
    private Semaphore semaphoreRelease;
    private Semaphore semaphoreAcquire;
    public PrintNumber(int mod,String threadName,
                       Semaphore semaphoreRelease,Semaphore semaphoreAcquire){
        this.mod = mod;
        this.threadName = threadName;
        this.semaphoreRelease = semaphoreRelease;
        this.semaphoreAcquire = semaphoreAcquire;
    }
    @Override
    public void run() {
        while(true){
            /*try {
                semaphoreRelease.acquire(0);
            }catch (InterruptedException e){
                //ignore
            }*/
            if(number > 100){
                return;
            }
            if(number % 3 == mod){
                System.out.println(threadName+"-->"+number);
                number++;
            }
            semaphoreAcquire.release();//调用一次增加一次允可，默认为1
        }
    }
}
