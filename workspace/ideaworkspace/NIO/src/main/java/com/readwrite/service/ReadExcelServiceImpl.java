package com.readwrite.service;

import com.readwrite.POI.POI;
import com.readwrite.entity.TableQ;
import lombok.Cleanup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ReadReplaceInsert基础上封装业务逻辑
 */
public class ReadExcelServiceImpl {

    //String[] head = {"表中文名","表英文名","字段中文名","字段英文名","字段类型","启用标志"};
    //int headSize = head.length;

    public static Map<Integer,Object> getInfo(String path,int headSize) throws Exception {
        int rowIndex =1;
        Map<Integer,Object> map =new HashMap<Integer,Object>();
        @Cleanup  //自动调用close（）
        InputStream inputStream = new FileInputStream(path);
        POI p =new POI();
        String fileName  = new File(path).getName();
        List<List<Object>> list = p.importExcel(inputStream,fileName);
        for (List<Object> ol:list) {
            //启用标志0跳过循环
            if ("0".equals(ol.get(ol.size()-1).toString())){
                continue;
            }
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
