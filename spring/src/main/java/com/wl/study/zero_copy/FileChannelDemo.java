package com.wl.study.zero_copy;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelDemo {
    public static final String CONTENT = "zero copy by FileChannel";
    public static final String SOURCE_FILE = "/zero_copy/source.txt";
    public static final String TARGET_FILE = "/zero_copy/target.txt";
    public static final String CHARSET = "UTF-8";

    //先将内容写入source.txt
    @Before
    public void setup(){
        Path path = Paths.get(getClass().getResource(SOURCE_FILE).getPath());
        byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
        try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ,
                                        StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING)){
            fileChannel.write(ByteBuffer.wrap(bytes));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //通过transferTo将fromChannel上的数据拷贝到toChannel
    @Test
    public void transferTo()throws Exception{
        try(FileChannel fromChannel = new RandomAccessFile(getClass().getResource(SOURCE_FILE).getPath()
                                                            ,"rw").getChannel();
            FileChannel toChannel = new RandomAccessFile(getClass().getResource(TARGET_FILE).getPath()
                                                            ,"rw").getChannel()){
            long position = 0L;
            long offset = fromChannel.size();
            fromChannel.transferTo(position,offset,toChannel);
        }
    }
    //通过transferFrom将fromChannel中的数据拷贝到toChannel
    @Test
    public void transferFrom()throws Exception{
        try(FileChannel fromChannel = new RandomAccessFile(getClass().getResource(SOURCE_FILE).getPath()
                                                            ,"rw").getChannel();
            FileChannel toChannel = new RandomAccessFile(getClass().getResource(TARGET_FILE).getPath()
                                                            ,"rw").getChannel()){
            long position = 0L;
            long offset = fromChannel.size();
            toChannel.transferFrom(fromChannel,position,offset);
        }
    }
}
