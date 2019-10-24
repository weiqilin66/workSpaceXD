package com.utils;

import java.io.*;
//读取 替换 写入
public class RRW {

    /**
     * 替换目标文本内容
     * @param fullContent 目标文本
     * @param changeStr 被替换的字符
     * @param replaceStr 替换的字符
     * @param raf RandomAccessFile
     * @throws IOException
     */
    public static void replace(String fullContent, String changeStr, String replaceStr, RandomAccessFile raf) throws IOException {

        String newContent = fullContent.replace(changeStr,replaceStr);
        System.out.println("修改后:");
        System.out.println(newContent);
        //newContent+="\n掐洗你!";
        raf.seek(0);
        raf.write(newContent.getBytes());
    }

    /**
     * 插入内容
     * @param raf
     * @param pos  指针位置
     * @param content  要插入的文本
     * @throws IOException
     */
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

    /**
     * 读取文本
     * @param raf
     * @return
     * @throws IOException
     */
    public static String RAFread(RandomAccessFile raf) throws IOException {
        String content = "";
        String str1, str2 = "";
        byte[] buffer = new byte[1024];
        int hasRead = raf.read(buffer);//实际读取字节数
        while (hasRead != -1) {

            str1 = new String(buffer);
            str2 = new String(str1.getBytes("utf-8"), "utf-8");
            content += str2;

            hasRead = raf.read(buffer);
        }

        return content;

    }
}
