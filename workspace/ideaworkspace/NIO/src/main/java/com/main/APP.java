package com.main;

import com.POI.POI;
import com.entity.TableQ;
import com.service.ReadExcelForX;
import com.utils.RRW;
import lombok.Cleanup;


import java.io.*;
import java.util.List;
import java.util.Map;

public class APP {
    public static void main(String[] args)  {
        File file;
        RandomAccessFile raf;
        //读取文本模板
        try {
            file = new File("E:\\testArea\\exmple.txt");
            raf = new RandomAccessFile(file,"rw");
            String blankStr = RRW.RAFread(raf);
            System.out.println("初始模本："+blankStr);
            //读取物理模型
            Map<Integer,Object> map = ReadExcelForX.getInfo();
            for(Map.Entry<Integer,Object> entry : map.entrySet()){
                //entry.getKey()   entry.getValue();
                System.out.println( entry.getValue());
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
