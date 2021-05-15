package com.wl.study.net.udp;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @Author:weilu
 * @Date:2020/5/13 19:44
 * @Description: ${Description}
 */
public class DatagramChannelClient {

    private Selector selector;

    public static void main(String[] args) throws IOException{
        DatagramChannelClient client = new DatagramChannelClient();
        client.initClient("10.13.154.174",10001);
        client.listen();
    }
    public void initClient(String ip ,int port)throws IOException{
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(ip,port));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        //发送数据到server
        channel.send(ByteBuffer.wrap(new String("Hello server!").getBytes()),new InetSocketAddress("10.13.154.174",10000));
    }
    public void listen()throws IOException{
        System.out.println("-----------client is start----------");
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isReadable()){
                    read(key);
                }
            }
        }
    }
    public void read(SelectionKey key)throws IOException{
        DatagramChannel channel = (DatagramChannel)key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        InetSocketAddress address = (InetSocketAddress)channel.receive(buffer);
        System.out.println("server ip and port: "+ address.getHostString()+","+address.getPort());
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("message from server:"+msg);
        channel.close();
    }
}
