package com.wl.study.zk.delay_queue;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author:weilu
 * @Date:2020/7/6 20:36
 * @Description: ${Description}
 */
public class LeaderSelectorClient extends LeaderSelectorListenerAdapter implements Closeable{

    private final LeaderSelector leaderSelector;
    public static List<String> messages = new ArrayList<>();

    public LeaderSelectorClient(CuratorFramework client,String path){
        leaderSelector = new LeaderSelector(client,path,this);
        leaderSelector.autoRequeue();
    }

    public void start(){
        leaderSelector.start();
    }
    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        while (true){
            for(String message:messages){
                if(StringUtils.isNotBlank(message)){
                    System.out.println("consume data:"+message+",currentTime:"+System.currentTimeMillis());
                }
            }
        }
    }

    public static void main(String[] args) {
        Queue<String> messages = new ArrayDeque<>();
        messages.add("test");
        System.out.println(messages.poll());
    }
}
