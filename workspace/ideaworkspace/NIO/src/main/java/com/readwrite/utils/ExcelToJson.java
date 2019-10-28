package com.readwrite.utils;

import com.readwrite.entity.TableQ;
import com.readwrite.service.ReadExcelServiceImpl;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;

public class ExcelToJson {
    public static String toJ( String excelPath,String headStr) throws Exception {

        Map<Integer,Object> map = ReadExcelServiceImpl.getInfo(excelPath);
        String Json ="";
        String str ="";
        for(Map.Entry<Integer,Object> entry : map.entrySet()){
            TableQ t =  (TableQ)entry.getValue();
            String key = ToCamelFormat.UnderlineToCamel(t.getColumUS());
            String value = ToCamelFormat.UnderlineToCamel(t.getColumCN());
            String tmpStr = "\t"
                    +"\""+key+"\""+" " +":"+" "
                    +"\""+ value+"\"" +","
                    +"\n";
            str+= tmpStr;
        }
        String JsonStr = headStr+" :"+"\n"
                +"{"+"\n"+str.substring(0,str.length()-3)
                +"\n"+"}";

        return JsonStr;

    }
}
