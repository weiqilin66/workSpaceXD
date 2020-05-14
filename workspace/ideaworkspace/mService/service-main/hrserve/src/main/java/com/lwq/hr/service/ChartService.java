package com.lwq.hr.service;

import com.lwq.hr.entity.Goods;
import com.lwq.hr.entity.TbKw;
import com.lwq.hr.mapper.GoodsMapper;
import com.lwq.hr.mapper.ShopMapper;
import com.lwq.hr.mapper.TbKwMapper;
import lwq.returnbean.RespBean;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Service
public class ChartService {
    @Resource
    TbKwMapper tbKwMapper;
    @Resource
    GoodsMapper goodsMapper;
    @Resource
    ShopMapper shopMapper;
    /**
     * @TODO
     * @return 读取Excel宝贝存入数据库
     * @date   2020/5/14
     */
    public RespBean loadExcelData(String excelPath) {
        List<TbKw> kwList = new ArrayList<>();
        try {
            File file = new File(excelPath);
            Workbook workbook = new XSSFWorkbook(file);
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                Sheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数(该行有数值的列数)
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    //8 循环行中列
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        Cell cell;
                        if (row.getCell(k) == null) {// k =1 但是第一列为空 第二列有值
                            cell = row.getCell(k + 1);
                        } else {
                            cell = row.getCell(k);
                        }
                        if (cell == null) {
                            System.out.println("读取excel异常");
                            break;
                        }
                        String cellValue = cell.getStringCellValue();
                        TbKw kw = new TbKw();
                        if (physicalNumberOfCells > 1 && k == 1 || row.getCell(k) == null) {
                            cellValue = "ps4 " + cellValue;
                            kw.setName(cellValue);
                            kw.setType("info");// 默认蓝色
                            kwList.add(kw);

                        } else {
                            cellValue = "switch " + cellValue;
                            kw.setName(cellValue);
                            kw.setType("info");
                            kwList.add(kw);
                        }
                    }
                }
            }
        } catch (IOException | InvalidFormatException e) {
            System.out.println(e + "\n打开excel失败");
            return RespBean.error("读取excel失败!");
        }
        // 存入数据库
        tbKwMapper.delAll();
        tbKwMapper.batchInsert(kwList);
        return RespBean.ok();
    }

    public List<Map<String, Object>> getMaxMin(String date){
        List<TbKw> totalList = tbKwMapper.selAll();
        List<Map<String, Object>> resList = new ArrayList<>();

        for (TbKw tbKw : totalList) {
            String goodName = tbKw.getName();
            String[] arr = goodName.split("\\s+");
            String condition = "";
            try {
                goodName = arr[0] + "%" + arr[1] + "%" + arr[2];
            } catch (ArrayIndexOutOfBoundsException ignored) {
                HashMap<String, Object> m = new HashMap<>();
                m.put("error","存在不符合 检索关键字规则的字段=> " + goodName);
                resList.add(m);
                return resList;
            }
            if (arr[2].contains("!")) {
                String[] cd = arr[2].split("!");
                condition = cd[1];
                goodName = arr[0] + "%" + arr[1] + "%" + cd[0];
            }
            Map<String, Object> map = new HashMap<>();
            List<Goods> maxList = goodsMapper.getMax(date, goodName,condition);
            List<Goods> minList = goodsMapper.getMin(date, goodName,condition);
            if (!maxList.isEmpty() && !minList.isEmpty()) {
                Goods max;
                Goods min;
                /**
                 * @TODO Lambda获取集合中某个属性最大的对象
                 * @param  maxList集合 sales对象中的属性
                 * @date 2020/5/13
                 */
                if (maxList.size() > 1) {// 价格而相同 取销量高的那家
                    Optional<Goods> maxOne = maxList.stream().max(Comparator.comparingInt(Goods::getSales));
                    max = maxOne.get();
                    map.put("max", max);
                    if (max.getSales()==99999) {//未获取到销量的加工成5个9 返回其他最大的集合
                        maxList.remove(max);
                        map.put("otherMax",maxList);
                    }
                } else {
                    max = maxList.get(0);
                    map.put("max", max);
                    map.put("otherMax",null);
                }

                if (minList.size() > 1) {// 价格而相同 取销量高的那家
                    Optional<Goods> minOne = minList.stream().max(Comparator.comparingInt(Goods::getSales));
                    min = minOne.get();
                    map.put("min", min);
                    if (min.getSales()==99999) {//未获取到销量的加工成5个9 返回其他最大的集合
                        minList.remove(min);
                        map.put("otherMin",minList);
                    }
                } else {
                    min = minList.get(0);
                    map.put("min", min);
                    map.put("otherMin",null);
                }
                float diff = max.getPrice() - min.getPrice();
                if (diff <20) {//差价大于20
                    continue;
                }
                map.put("diff", diff);
                String rName = goodName.replace("%", " ");
                map.put("goodName",rName);
                resList.add(map);
            }
        }
        return resList;
    }
}
