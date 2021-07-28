package threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return nextId.getAndIncrement();
        }
    };
    private static final ThreadLocal<String> tl = new ThreadLocal<>();
    //或者这样也行
    //private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());
    public static int getNextId(){
        return threadId.get();
    }

    public static void main(String[] args) {
        //int nextId = getNextId();
        //System.out.println("nextId:"+nextId);
        tl.set("test");
        System.out.println(tl.get());
        tl.set("test2");
        System.out.println(tl.get());
    }
}
