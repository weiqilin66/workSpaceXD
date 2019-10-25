package com.readwrite.service;

import com.readwrite.entity.TableQ;
import com.readwrite.utils.ReadReplaceInsert;
import com.readwrite.utils.ToCamelFormat;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

public class WriteVue {
    /**
     *
     * @param blankpath  模板路径
     * @param excelPath  excel路径
     * @param targetPath 目标文件路径
     * @param headSize  excel列数 默认6
     */
    public static void mvvm(String blankpath,String excelPath ,String targetPath,int headSize){
        File file;
        RandomAccessFile raf;
        String txt = "";
        //读取文本模板
        try {
            file = new File(blankpath);
            //file = new File("src/nomal_io.txt");
            raf = new RandomAccessFile(file,"rw");
            String blankStr = ReadReplaceInsert.RAFreadTxt(raf);
            System.out.println("初始模本："+blankStr);
            //读取物理模型
            Map<Integer,Object> map = ReadExcelServiceImpl.getInfo(excelPath,headSize);
            for(Map.Entry<Integer,Object> entry : map.entrySet()){
                TableQ t =  (TableQ)entry.getValue();
                System.out.println("第"+entry.getKey()+"行数据结构:"+t);
                //整合模本 excel数据
                String columStr = ToCamelFormat.UnderlineToCamel(t.getColumUS()) ;
                String tmpStr = blankStr.replace("3721",columStr);
                txt+= tmpStr+"\n";
                System.out.println("整合数据:"+txt);
            }
            //读取最终目标文件
            file = new File(targetPath);
            raf = new RandomAccessFile(file,"rw");
            String targetStr = ReadReplaceInsert.RAFreadTxt(raf);
            System.out.println("目标文本："+targetStr);

            //替换插入文本
            String target = targetStr.replace("3721",txt);
            raf.seek(0);
            raf.write(txt.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
