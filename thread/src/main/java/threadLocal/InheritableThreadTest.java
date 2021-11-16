package threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InheritableThreadTest {
    /*private static final InheritableThreadLocal<String> itl = new InheritableThreadLocal<>();
    public void test(){
        itl.set("super test");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                itl.set("self test");
                System.out.println(itl.get());
            }
        });
    }

    public static void main(String[] args) {
        InheritableThreadTest test = new InheritableThreadTest();
        test.test();

    }*/

}
