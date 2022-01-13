package thread_ordered;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrint {
    private static final Lock lock = new ReentrantLock(false);
    private static Condition cOver = lock.newCondition();
    private static Condition aOver = lock.newCondition();
    private static Condition bOver = lock.newCondition();

    public static void main(String[] args) {
        new PrintThread(1, "Thread-A", lock, cOver, bOver).start();
        new PrintThread(2, "Thread-B", lock, aOver, cOver).start();
        new PrintThread(0, "Thread-C", lock, bOver, aOver).start();
    }
}

class PrintThread extends Thread {
    private int mod;
    private String name;
    private static int number = 1;
    private Lock lock;
    private Condition wait;
    private Condition notify;

    public PrintThread(int mod, String name, Lock lock, Condition wait, Condition notify) {
        this.mod = mod;
        this.name = name;
        this.lock = lock;
        this.notify = notify;
        this.wait = wait;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (number > 100) {
                    System.out.println(name + "over");
                    notify.signal();
                    break;
                }
                if (number % 3 == mod) {
                    System.out.println(name + "  =>  " + number);
                    number++;
                }
                notify.signal();
                wait.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }
}