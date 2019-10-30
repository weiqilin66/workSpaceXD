package com.readwrite.service;

import com.readwrite.POI.POI;
import com.readwrite.entity.TableQ;
import lombok.Cleanup;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

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
    /**
     *
     * @param path  excel地址
     * @return  返回一个<行,行对象>的map
     * @throws Exception
     */
    public static Map<Integer,Object> getInfo(String path) throws Exception {
        int rowIndex =1;
        int headSize = 0;
        Map<Integer,Object> map =new HashMap<Integer,Object>();
        @Cleanup  //自动调用close（）
        InputStream inputStream = new FileInputStream(path);
        //headSize = new HSSFWorkbook(inputStream).getSheetAt(0).getRow(0).getLastCellNum();
        //以上发现bug nio读写操作失败, 流线程阻塞;
        POI p =new POI();
        String fileName  = new File(path).getName();
        List<List<Object>> list = p.importExcel(inputStream,fileName);
        headSize=list.get(0).size();
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
            //逻辑上不应该用实体类接,否则字段数固定了
            TableQ t  =new TableQ(tmpStr[0],tmpStr[1],tmpStr[2],tmpStr[3],tmpStr[4],tmpStr[5]);
            map.put(rowIndex,t);
            rowIndex++;
        }
        return map;
    }

}
