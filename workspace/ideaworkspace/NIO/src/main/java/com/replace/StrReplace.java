package com.replace;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class StrReplace {
    public static void sr(String key, String content, RandomAccessFile raf) throws IOException {
        String str1, str2;
        String fullContent = "";
        String newContent;
        byte[] buffer = new byte[1024];
        int hasRead = raf.read(buffer);//实际读取字节数
        while (hasRead != -1) {

            str1 = new String(buffer);
            str2 = new String(str1.getBytes("utf-8"), "utf-8");
            fullContent += str2;

            hasRead = raf.read(buffer);
        }
        System.out.println(fullContent);
        newContent = fullContent.replace("林伟奇","帅奇");
        System.out.println("修改后:");
        System.out.println(newContent);
        //newContent+="\n掐洗你!";
        raf.seek(0);
        raf.write(newContent.getBytes());
    }

    public static void main(String[] args) throws IOException {
        File file = new File("src/nomal_io.txt");

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        StrReplace.sr("", "", raf);
    }
}
