package com.learn;

import java.io.*;

public class RandomAccessFileTest {
    private static final String PATH ="src/nomal_io.txt";
    public static void main(String[] args) throws IOException {
        method();
        //中文占用3个pos
        //method2(17,"爽歪歪");
    }
    public static void method() throws IOException {

        File file=null;
        RandomAccessFile raf = null;
        String str1=null;
        String str2=null;
        try {
             file = new File(PATH);
             raf = new RandomAccessFile(file,"rw");
             //位移到指定位置
             //raf.seek(2);
            //System.out.println("指针当前位置为" + raf.getFilePointer());
            byte[] buf=new byte[23];
            int bytesRead = raf.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    str1 = new String(buf);
                    str2 = new String(str1.getBytes("ISO-8859-1"), "utf-8");
                    //System.out.print((char) buf[i]);
                    System.out.println(str2);

                }
                //当文件过大时需要循环读取,并且给bytesRead重新赋值判断是否结束
                bytesRead = raf.read(buf);

            }
            //将记录指针移动到该文件的最后
            raf.seek(raf.length());
            //向文件末尾追加内容
            //raf.writeChars("这是追加内容。。");  乱码!
            //raf.write("这是追加内容。。".getBytes());
            //writeUTF有bug,使用上面的方法
            //raf.writeUTF("这是追加内容。。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            raf.close();
        }
    }

    //指定位置插入数据
    public static void method2(long pos,String insertContent) throws IOException {
        RandomAccessFile raf = null;
        File tmp=File.createTempFile("tmp",null);
        tmp.deleteOnExit();
        raf = new RandomAccessFile(PATH,"rw");
        //创建一个临时文件来保存插入点后的数据
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
        raf.write(insertContent.getBytes());
        //追加临时文件中的内容
        while ((hasRead = fileInputStream.read(bbuf)) != -1) {
            //将读取的内容写入临时文件
            raf.write(bbuf, 0, hasRead);
        }
    }


}
