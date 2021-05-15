package com.wl.study.callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author:weilu
 * @Date:2020/4/27 20:54
 * @Description: ${Description}
 */
public class Bootstrap {

    /**
     * 输出：先输出main,暂停3s后再输出Thread-0和hello world
     * 说明新开的线程执行完了之后，回调了主线程里的监听
     * @param args
     */
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        Worker worker = bootstrap.newWork();//耗时的任务
        Wrapper wrapper = new Wrapper();
        wrapper.setWorker(worker);
        wrapper.setParam("hello");//如果不传参数，则会回调异常的分支
        //先添加监听器再调用doWork，不然line47可能会出现空指针异常
        //像bootstrap.doWork(wrapper).addListener(xxx)...这种写法可能会有问题，要是子线程响应很快则有可能会出现上面说的空指针问题
        //很不错的一个地方：这里add不同的Listener就可以达到不同的回调功能，这里相当于是可变的部分
        //https://www.iteye.com/blog/mushme-1278291
        wrapper.addListener(new Listener() {
            //监听回调,等新开线程任务执行完后再执行这里
            @Override
            public void result(Object result) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(result);
            }
            //异常回调，如果发生异常则会执行
            @Override
            public void exception(String msg) {
                System.out.println("exception info:"+msg);
            }
        });
        bootstrap.doWork(wrapper);
        System.out.println(Thread.currentThread().getName());

    }

    private Wrapper doWork(Wrapper wrapper){
        new Thread(() -> {
            Worker worker = wrapper.getWorker();
            Object param = wrapper.getParam();
            if(param == null){//进行异常判断
                wrapper.getListener().exception("参数为空");
            }else{
                String result = worker.action(param);//执行任务
                //下面getListener()获取到的Listener对象就是line28行的new Listener()对象
                wrapper.getListener().result(result);//任务执行完之后进行回调
            }
        }).start();

        return wrapper;
    }

    /**
     * 具体执行的任务
     * @return
     */
    private Worker newWork(){
        return new Worker() {
            @Override
            public String action(Object object) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                return object + " world";
            }
        };
    }
}
