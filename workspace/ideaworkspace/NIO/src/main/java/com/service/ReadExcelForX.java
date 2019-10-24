package com.service;

import com.POI.POI;
import com.entity.TableQ;
import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcelForX {

    //String[] head = {"表中文名","表英文名","字段中文名","字段英文名","字段类型","启用标志"};
    //int headSize = head.length;

    public static Map<Integer,Object> getInfo() throws Exception {
        int rowIndex =1;
        int headSize= 6;
        Map<Integer,Object> map =new HashMap<Integer,Object>();
        @Cleanup  //自动调用close（）
        InputStream inputStream = new FileInputStream("E:/testArea/物理模型.xls");
        POI p =new POI();
        List<List<Object>> list = p.importExcel(inputStream,"物理模型.xls");
        for (List<Object> ol:list) {
            String[] tmpStr =new String[headSize];
            int index =0;
            for (Object obj:ol) {
              tmpStr[index]= obj.toString();
                index++;
            }
            TableQ t  =new TableQ(tmpStr[0],tmpStr[1],tmpStr[2],tmpStr[3],tmpStr[4],tmpStr[5]);
            map.put(rowIndex,t);
            rowIndex++;
        }
        return map;
    }

}
