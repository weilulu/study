package com.wl.study.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;

/**
 * @Author:weilu
 * @Date: 2019/4/23 18:56
 * @Description: zk的一些常用操作
 */
public class ZkBaseOperator {
    public static final CountDownLatch CONNECT_SIGNAL = new CountDownLatch(1);
    private static final String CONNECT_URL = "localhost:2182";
    private static final Integer WAITING = 1;
    private static final Integer SECOND = 1000;

    public static void main(String[] args)throws Exception{
        Watcher watcher = new DefaultWatcher();
        //创建连接，异步操作，需要手动处理等待
        ZooKeeper zk = new ZooKeeper(CONNECT_URL,2 * SECOND,watcher);
        //CONNECT_SIGNAL.await();//主线程调用await时，会将自己阻塞在这个方法上，直到其它线程各自完成任务
        System.out.println("ZK server connected...");
        //同步操作
        System.out.println("--Sync operations--");
        new ZkBaseOperator().syncOperations(zk,watcher);
        //异步操作
        //System.out.println("--Async operattions--");
        //new ZkBaseOperator().asyncOperations(zk);
        //带权限操作
        //System.out.println();
        //new ZkBaseOperator().authOperations(watcher);
    }

    /**
     * 同步操作
     * @param zk
     * @param watcher
     * @throws Exception
     */
    private void syncOperations(ZooKeeper zk,Watcher watcher)throws Exception{
        //Thread.sleep(WAITING * SECOND);
        Stat stat = zk.exists("/zk_sync_test",true);
        if(stat == null){
            //创建节点
            zk.create("/zk_sync_test","ZK NODE TEST".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        //Thread.sleep(WAITING * SECOND);
        //stat = zk.exists("/zk_sync_test",true);
        //获得和修改节点内容
        if(stat != null){
            System.out.println("path 'zk_sync_test' data before change : " + new String(zk.getData("/zk_sync_test",true,stat)));
            zk.setData("/zk_sync_test","ZK NODE TESTING".getBytes(),stat.getVersion());
            System.out.println("path 'zk_sync_test' data after change : "+new String(zk.getData("/zk_sync_test",true,stat)));
        }
        Thread.sleep(WAITING * SECOND);
        stat = zk.exists("/zk_sync_test",true);
        //创建子节点
        if(stat != null){
            if(zk.exists("/zk_sync_test/child",watcher) == null){
                zk.create("/zk_sync_test/child","ZK CHILD NODE TEST".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
        }
        Thread.sleep(WAITING * SECOND);
        stat = zk.exists("/zk_sync_test/child",true);
        //删除子节点
        if(stat != null){
            zk.delete("/zk_sync_test/child",stat.getVersion());
        }
        Thread.sleep(WAITING * SECOND);
        stat = zk.exists("/zk_sync_test",true);
        //删除节点
        if(stat != null){
            zk.delete("/zk_sync_test",stat.getVersion());
        }

        zk.close();
        System.out.println("ZK server closed");
    }

    /**
     * 异步操作
     * @param zk
     * @throws Exception
     */
    private void asyncOperations(ZooKeeper zk)throws Exception{
        Thread.sleep(WAITING * SECOND);
        Stat stat = zk.exists("/zk_async_test",true);
        //异步创建节点
        if(stat == null){
            zk.create("/zk_async_test", "ZK NODE".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,
                    new AsyncCreateCallBack(),"context ...");
        }
        Thread.sleep(WAITING * SECOND);
        stat = zk.exists("/zk_async_test",true);
        if(stat != null){
            zk.getData("/zk_async_test",true,new AsyncGetCallBack(),"before context change");
            zk.setData("/zk_async_test","ZK NODE CHANGE".getBytes(),
                    stat.getVersion(),new AsyncChangeCallBack(),"changing context");
            zk.getData("/zk_async_test",true,new AsyncGetCallBack(),"changed contex");
        }
        Thread.sleep(WAITING * SECOND);
        stat = zk.exists("/zk_async_test",true);
        if(stat != null){
            //删除节点
            if(stat != null){
                zk.delete("/zk_async_test",stat.getVersion());
            }
        }

    }

    /**
     * 带权限的操作
     * @param watcher
     * @throws Exception
     */
    public void authOperations(Watcher watcher)throws Exception{
        String authPath = "/zk_sync_test";
        String authChildPath = "/zk_sync_test/child";
        String schemaDigest = "digest";
        String nameAndPwd = "username:password";
        ZooKeeper zk1 = new ZooKeeper(CONNECT_URL,2 * SECOND,watcher);
        CONNECT_SIGNAL.await();
        Thread.sleep(WAITING * SECOND);
        //添加权限信息，其中digest的schema下需要指定用户名和密码
        zk1.addAuthInfo(schemaDigest,nameAndPwd.getBytes());
        Stat stat = zk1.exists(authPath,true);
        if(stat == null){
            zk1.create(authPath,"auth content".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL,CreateMode.PERSISTENT);
        }

        ZooKeeper zk2 = new ZooKeeper(CONNECT_URL,2*SECOND,watcher);
        CONNECT_SIGNAL.await();
        Thread.sleep(WAITING*SECOND);
        try {
            stat = zk2.exists(authPath, false);
            if (stat != null) {
                //无权限获取节点内容失败
                System.out.println(new String(zk2.getData(authPath, false, null)));
            }
        }catch (Exception e){
            System.out.println("Get data failed cause have no authorize information.");
        }
        Thread.sleep(WAITING*SECOND);
        zk2.addAuthInfo(schemaDigest,nameAndPwd.getBytes());
        stat = zk2.exists(authPath,false);
        if(stat != null){
            //带权限获取节点内容，成功
            System.out.println("Get data from path ["+authPath+"] after add the authorize information:"
                    + new String(zk2.getData(authPath,false,stat)));
        }

        ZooKeeper zk3 = new ZooKeeper(CONNECT_URL,2 * SECOND,watcher);
        CONNECT_SIGNAL.await();
        Thread.sleep(WAITING * SECOND);
        try {
            stat = zk3.exists(authChildPath,false);
            if(stat != null){
                //无权限删除子节点，失败
                zk3.delete(authChildPath,stat.getVersion());
            }
        }catch (Exception e){
            System.out.println("Delete the child node ["+authChildPath+"] sucess after add the authorize information");
        }

        ZooKeeper zk4 = new ZooKeeper(CONNECT_URL,2*SECOND,watcher);
        CONNECT_SIGNAL.await();
        Thread.sleep(WAITING*SECOND);
        stat = zk4.exists(authPath,false);
        if(stat != null){
            //删除根节点不需要权限
            zk4.delete(authPath,stat.getVersion());
            System.out.println("Delete the root node["+authPath+"] do not use the authorize information");
        }
    }
    static class AsyncCreateCallBack implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int rc,String path,Object ctx,String name){
            System.out.println("Async create path [" + rc + "," + path + "," + ctx + "," + name + "]");
        }
    }

    static class AsyncGetCallBack implements AsyncCallback.DataCallback {
        @Override
        public void processResult(int rc,String path,Object ctx,byte[] data,Stat stat){
            System.out.println("Async get path ["+rc+","+path+","+ctx);
            System.out.println("["+stat.getCzxid()+","+stat.getMzxid()+","+stat.getVersion());
        }
    }

    static class AsyncChangeCallBack implements AsyncCallback.StatCallback {
        @Override
        public void processResult(int rc,String path,Object ctx,Stat stat){
            if(rc == 0){
                System.out.println("Async change path ["+rc+","+path+","+ctx+"] SUCCESS");
            }
        }
    }
    static class DefaultWatcher implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            switch (event.getType()){
                case NodeCreated:
                    System.out.println(MessageFormat.format("Create note on path [{0}]",event.getPath()));
                    break;
                case NodeDeleted:
                    System.out.println(MessageFormat.format("Delete node path [{0}]",event.getPath()));
                    break;
                case NodeDataChanged:
                    System.out.println(MessageFormat.format("Data changed on path [{0}]",event.getPath()));
                    break;
                case NodeChildrenChanged:
                    System.out.println(MessageFormat.format("Children changed on path [{0}]",event.getPath()));
                    break;
                case None:
                default:
                    System.out.println(MessageFormat.format("Unknow operations on path [{0}]",event.getPath()));

                switch (event.getState()){
                    case Disconnected:
                        System.out.println("Connection state : disconnected");
                        break;
                    case Expired:
                        System.out.println("Connection state : expired");
                        break;
                    case NoSyncConnected:
                        System.out.println("Connection state : no sync connected");
                        break;
                    case SyncConnected:
                        System.out.println("Connection state sync connected");
                        break;
                    case Unknown:
                    default:
                        System.out.println("Connection state : unknow");
                        break;
                }
                break;
            }
        }
    }
}
