package base;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:weilu
 * @Date:2020/5/23 17:16
 * @Description: 中断学习
 *
 * 中断的使用场景：
 * 1,点击某个桌面应用中的取消按钮时；
    2,某个操作超过了一定的执行时间限制需要中止时；
    3,多个线程做相同的事情，只要一个线程成功其它线程都可以取消时；
    4,一组线程中的一个或多个出现错误导致整组都无法继续时；
    5,当一个应用或服务需要停止时。

    interrupt只会将处于sleep或者wait状态的线程唤醒并抛出interrupt exception。
    如果整个线程中没有sleep或者wait，调用了interrupt也不会使线程终止，因为线程找不到合适的时机。
    所以对于没有sleep或者wait的线程，我们一般采用变量检查的方式来使得线程可以在某一阶段退出。
    http://ifeve.com/java-interrupt-mechanism/
 */
public class SomeTest {

    @Test
    public void interruptTest1(){
        Thread.currentThread().interrupt();
        try {
            Thread.sleep(3);//如果当前线程被中断了，再调用sleep方法时会抛出中断异常,两个都会输出
        }catch (InterruptedException e){
            System.out.println("interrupt occurred");
        }
        System.out.println("end");
    }

    @Test
    public void interruptTest2(){
        Thread.currentThread().interrupt();
        try {
            while(!Thread.currentThread().isInterrupted()){//不会改变线程的中断标识,只会输出end
                Thread.sleep(3);
            }
        }catch(InterruptedException e){
            System.out.println("interrupt occurred");
        }
        System.out.println("end");
    }

    @Test
    public void interruptTest3(){
        for(int i=0;i<10;i++){
            Thread.currentThread().isInterrupted();//调用interrupt方法并不会使用线程中断
            System.out.println(i);
            Thread.interrupted();//也不会中断
            System.out.println(i+"--");
        }
    }

    /**
     * 下面这个例子本想在var=6时让子线程中断，但失败了，结果仍然输出10
     */
    int var = 0;
    @Test
    public void interruptTest4(){
        CountDownLatch latch = new CountDownLatch(1);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(var ++);
                }
            }
        },"no.4");
        t.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(var == 6){
            t.interrupt();
        }
        System.out.println(var);
    }

    private static final byte OPCODE = 0x01;
    @Test
    public void testSplit(){
        System.out.println(" TEST".length());
        System.out.println((OPCODE+" TEST").split(Byte.toString(OPCODE))[1].length());
        System.out.println("  TEST".length());
        System.out.println("  TEST".split("  ")[1].length());
    }

    /**
     * 将特殊字段替换转译符 \
     * 如果直接使用string.replaceAll方法会出错，可看String的replaceAll方法上的注释
     * $　这个符号也需要这样处理
     *
     * Note that backslashes ({@code \}) and dollar signs ({@code $}) in the
     * replacement string may cause the results to be different than if it were
     * being treated as a literal replacement string; see
     * {@link java.util.regex.Matcher#replaceAll Matcher.replaceAll}.
     * Use {@link java.util.regex.Matcher#quoteReplacement} to suppress the special
     * meaning of these characters, if desired.
     */
    @Test
    public void testReplaceBackslashes(){
        String test = "test#test";
        Pattern p = Pattern.compile("#");
        Matcher m = p.matcher(test);
        String s = m.replaceAll(Matcher.quoteReplacement("\\"));
        System.out.println(s);//输出test\test
    }
}
