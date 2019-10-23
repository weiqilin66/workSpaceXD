package com.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * 问题记录1 文档中有中文时,中文是2个字节(16byte) 英文字符是1个字节 如何读取判断
 * 方案1 使用readLine()
 * readLine()整行读取 都会将编码格式转换成 ISO-8859-1  所以 输出显示是还要在进行一次转码
 * new String(str.getBytes("ISO-8859-1"),"UTF-8");
 * <p>
 * BUG:
 * //Map相同key会覆盖之前数据
 * 方案记录(查找替换)
 *  还可以使用readline()读取所有字符后 str+=str+xx 使用 String.replace(param1,param2) 替换
 *  实现 StrReplace
 *
 */
public class SearchPosition {
    /**
     * skipBytes(int n) 思路 一个个字节往后取   总会匹配上
     * //ISO-8859-1码制字节数: 字母1 汉字3 数字1
     * 开发记录:实现首行获取精确pos,默认文件只有一个这个值
     *
     * @return
     */
    //keword结束的pos
    public static long exactPos(RandomAccessFile raf, String keyword) throws IOException {
        String str = null;//和String str;等价已初始化
        long len = 0;//基本数据类型则必须初始化 =0 或者某个值
        long pos = 0;
        int line = 1;
        long exactPos = 0;
       List<Long> lineList = new ArrayList<Long>();
        byte[] buffer = null;

        len = raf.length();
        System.out.println("文件长度" + len);
        do {
            buffer = raf.readLine().getBytes("ISO-8859-1");
            str = new String(buffer, "utf-8");
            System.out.println(str);
            //当前位置
            pos = raf.getFilePointer();
            //存储当前行末pos信息
            lineList.add(pos);
            //判断此行是否含有keyword
            if (str.contains(keyword)) {
                System.out.println("第" + line + "行包含keyword");
                //判断字符类型,暂时写死中文
                int check = CnOrUs.checkStr(keyword);
                int strSize = 0;
                switch (check) {
                    case 1:
                        strSize = keyword.length() * 3;
                        break;
                }
                byte[] buf = new byte[strSize];
                if (line == 1) {
                    for (int x = 0; x < pos; x++) {
                        raf.seek(x);
                        int bytesRead = raf.read(buf);
                        if (bytesRead != -1) {
                            String str1 = new String(buf);
                            String str2 = new String(str1.getBytes("utf-8"), "utf-8");
                            System.out.println("检索第" + x + "次" + ",检索到字符长度为" + strSize + "的字符为" + str2);
                            if (str2.equals(keyword))
                                exactPos = raf.getFilePointer();
                        }
                    }
                }else{

                    Long start = lineList.get(line-1);
                    long end = lineList.get(line);
                    System.out.println("第"+line+"行开始pos始于"+start+",结束于"+end);
                    for (long x = start; x < end; x++) {
                        raf.seek(x);
                        int bytesRead = raf.read(buf);
                        if (bytesRead != -1) {
                            String str1 = new String(buf);
                            String str2 = new String(str1.getBytes("utf-8"), "utf-8");
                            System.out.println("检索第" + x + "次" + ",检索到字符长度为" + strSize + "的字符为" + str2);
                            if (str2.equals(keyword))
                                exactPos = raf.getFilePointer();
                        }
                    }
                }
                raf.seek(pos);
                line++;
            }
        } while (pos < len);
        return exactPos;
    }


    //返回行
    public static Map<Integer, String> position(RandomAccessFile raf, List<String> keywordList, File file) {
        String str = null;
        long len = 0;
        long pos = 0;
        int line = 0;
        int keywordIndex = 0;
        Map<String, Long> exactPosMap = new LinkedHashMap<String, Long>();
        Map<Integer, String> linePosMap = new LinkedHashMap<Integer, String>();
        Map<Integer, Long> lineMap = new HashMap<Integer, Long>();
        byte[] buffer = null;

        try {
            //读模式
            //raf = new RandomAccessFile(file,"r");
            len = raf.length();
            //默认G长度:字母2 函数3 数字1
            System.out.println("文件长度" + len);
            do {
                // String.getBytes(String decode)方法会根据指定的decode编码返回某字符串在该编码下的byte数组表示
                //ISO-8859-1码制字节数: 字母1 汉字3 数字1
                buffer = raf.readLine().getBytes("ISO-8859-1");
                //new String(byte[], decode)实际是使用decode指定的编码来将byte[]解析成字符串
                str = new String(buffer, "utf-8");

                System.out.println(str);
                //当前位置
                pos = raf.getFilePointer();
                //存储当前行末pos信息
                lineMap.put(line, pos);
                if (!keywordList.isEmpty()) {
                    for (String keyword : keywordList) {
                        if (str.contains(keyword)) {
                            System.out.println("所在行" + line);
                            if (lineMap.size() == 1) {
                                //第一行出现关键字
                                linePosMap.put(line, keyword);

                            } else {
                                //Map相同key会覆盖之前数据
                                linePosMap.put(line, keyword);
                            }


                        }

                        keywordIndex++;
                    }
                }

                line++;
            } while (pos < len);//条件成立继续循环 与while相比至少执行一次
            //遍历行逻辑2
            /*
            while (true){
                long len1 = raf.length();
                if (len1>len){
                    raf.seek(len);
                    str1 = new String(raf.readLine().getBytes("ISO-8859-1"),"utf-8");
                    while(str1 != null){
                        System.out.println(str1);
                        str1 = raf.readLine();
                    }
                    len = len1;
                }
            }

             */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }


        return linePosMap;
    }
}
