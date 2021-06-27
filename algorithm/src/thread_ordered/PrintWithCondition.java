package thread_ordered;

import java.util.concurrent.Semaphore;

public class PrintWithCondition {
    public static void main(String[] args) {
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        new Print(1,s1,s2).start();
        new Print(0,s2,s1).start();
    }
}

class Print extends Thread{
    private static volatile int number = 1;
    private int mod;
    private Semaphore acquire;
    private Semaphore release;
    public Print(int mod,Semaphore acquire,Semaphore release){
        this.mod = mod;
        this.acquire = acquire;
        this.release = release;
    }

    @Override
    public void run(){
        while(true){
            try {
                acquire.acquire();
            }catch (InterruptedException e){
                //ignore
            }
            if(number > 10){
                return;
            }
            if(number % 2 == mod){
                System.out.println(Thread.currentThread()+"---"+number);
                number++;
            }
            release.release();
        }
    }
}
