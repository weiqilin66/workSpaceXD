package com.readwrite.service;

import com.readwrite.entity.TableQ;
import com.readwrite.utils.ExcelToJson;
import com.readwrite.utils.ToCamelFormat;
import sun.rmi.transport.ObjectTable;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Map;

public class WriteJSON {
    /**
     *
     * @param excelPath
     * @param targetPath 目标文件写入
     * @param headSize excel的字段数 默认6
     * @param headStr json的主key
     * @throws Exception
     */
    public static void writeJ(String excelPath,String targetPath,int headSize,String headStr) throws Exception {
        String JsonStr = ExcelToJson.toJ(excelPath,headSize,headStr);

        File file = new File(targetPath);
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        long index = raf.length();
        raf.seek(index);
        raf.write(
                (",\n"+JsonStr).getBytes());
    }
}
