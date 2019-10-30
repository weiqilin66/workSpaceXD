package com.learn;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Nio {
    public static void main(String[] args) {
        method1();
    }

    /**
     *  Buffer顾名思义：缓冲区，实际上是一个容器，一个连续数组。
     *  Channel提供从文件、网络读取数据的渠道，但是读写的数据都必须经过Buffer
     *  调用clear() 缓冲区的索引位置又回到了初始位置
     */
    public static void method1(){
        RandomAccessFile aFile = null;
        try{
          //RandomAccessFile 操作模式rw:读写
            aFile = new RandomAccessFile("src/nio.txt","rw");
            //声明通道
            FileChannel fileChannel = aFile.getChannel();
            //分配空间
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //Channel写入数据到Buffer
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while(bytesRead != -1)
            {
                //写入Channel的通信信道
                buf.flip();
                while(buf.hasRemaining())
                {
                    //从Buffer中读取数据buf.get()
                    System.out.print((char)buf.get());
                }
                //compact()方法将所有未读的数据拷贝到Buffer起始处
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
