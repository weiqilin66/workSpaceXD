package com.utils;

import java.io.*;

public class InsertContent {

    public static void insert(RandomAccessFile raf,int pos,String content) throws IOException {
        //创建一个临时文件来保存插入点后的数据
        File tmp = File.createTempFile("tmp", null);
        tmp.deleteOnExit();
        FileOutputStream fileOutputStream = new FileOutputStream(tmp);
        FileInputStream fileInputStream = new FileInputStream(tmp);
        //把文件记录指针定位到pos位置
        raf.seek(pos);
        //------下面代码将插入点后的内容读入临时文件中保存-----
        byte[] bbuf = new byte[64];
        //用于保存实际读取的字节数据
        int hasRead = 0;
        //使用循环读取插入点后的数据
        while ((hasRead = raf.read(bbuf)) != -1) {
            //将读取的内容写入临时文件
            fileOutputStream.write(bbuf, 0, hasRead);
            System.out.println("写入临时文件");
        }
        //-----下面代码用于插入内容 -----
        //把文件记录指针重新定位到pos位置
        raf.seek(pos);
        //追加需要插入的内容
        raf.write(content.getBytes());
        //追加临时文件中的内容
        while ((hasRead = fileInputStream.read(bbuf)) != -1) {
            //将读取的内容写入临时文件,末尾写入
            raf.write(bbuf, 0, hasRead);
        }

    }

}
