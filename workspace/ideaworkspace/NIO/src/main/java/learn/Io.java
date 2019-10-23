package learn;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Io {
    public static void main(String[] args) {
            method2();
    }

    public static void method2() {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("src/nomal_io.txt"));
            //定义了一个byte类型的数组，数组长度为1024
            byte[] buf = new byte[1024];
            /**
             *1.read () 方法，这个方法 从输入流中读取数据的下一个字节。返回 0 到 255 范围内的 int 字节值。
             * 如果因为已经到达流末尾而没有可用的字节，则返回值 -1 。
             *2.read (byte[] b,int off,int len) 方法， 将输入流中最多 len 个数据字节读入 byte 数组
             * 。尝试读取 len 个字节，但读取的字节也可能小于该值。以整数形式返回实际读取的字节数。
             *3.read (byte[] b) 方法， 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。
             * 以整数形式返回实际读取的字节数。
             */

            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);
                //当文件过大时需要循环读取
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
