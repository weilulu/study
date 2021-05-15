package com.wl.study.net.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @Author:weilu
 * @Date:2020/5/13 17:42
 * @Description: 先启动server再启client才会有数据的交互
 * DatagramChannel是一个能发送和接受UDP数据包的channel，
 */
public class DatagramChannelServer {
    private Selector selector;

    public static void main(String[] args) throws IOException{
        DatagramChannelServer server = new DatagramChannelServer();
        server.initServer("10.13.154.174",10000);
        server.listen();
    }
    public void initServer(String host,int port)throws IOException{
        //get the ServerSocket
        DatagramChannel serverChannel = DatagramChannel.open();
        //set no blocking mode
        serverChannel.configureBlocking(false);
        //bind the port
        serverChannel.socket().bind(new InetSocketAddress(host,port));
        //get the channel manager
        selector = Selector.open();
        //Register the channel to manager and bind the event，将channel注册到selector上
        //selectKey表示某个channel注册在selector上
        serverChannel.register(selector, SelectionKey.OP_READ);
    }
    public void listen()throws IOException{
        System.out.println("---------server is start!--------------");
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    /**
     * deal with the message from the client
     * @param key
     */
    public void read(SelectionKey key)throws IOException{
        DatagramChannel channel = (DatagramChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        //receive方法会将接收到的数据包的内容入到给定的Buffer中，如果接收到的包中含有的数据比Buffer的容量要大的话，余下的数据会被抛弃
        InetSocketAddress socketAddress = (InetSocketAddress)channel.receive(buffer);
        System.out.println("client ip and port : "+socketAddress.getHostString()
                            +", "+socketAddress.getPort());
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("message from client: "+msg);
        //使用send发送数据,发送出去之后不会关注是否被收到
        channel.send(ByteBuffer.wrap(new String("Hello Client!").getBytes()),socketAddress);
        channel.close();
    }

}
