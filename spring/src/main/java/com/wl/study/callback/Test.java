package com.wl.study.callback;

import java.util.concurrent.Callable;

/**
 * @Author:weilu
 * @Date:2020/5/9 21:01
 * @Description: 测试用的，没什么作用
 */
public class Test {
//    public static void main(String[] args) {
//        Callable<String> process = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                System.out.println("hello");
//                return "result";
//            }
//        };
//        RetryLoop loop = new RetryLoop();
//        String result = loop.callWithRetry(process);
//        System.out.println(result);
//        //先输出hello,再输出result
//    }
//
//    static class RetryLoop{
//        public <T> T callWithRetry(Callable<T> proc){
//            T result = null;
//            try {
//                result = proc.call();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            return result;
//        }
//    }
public static void main(String[] args) {
    System.out.println(Long.parseLong("173BEDCC927",16));
    System.out.println(String.format("%08X", 1596635007271L));
}

}
