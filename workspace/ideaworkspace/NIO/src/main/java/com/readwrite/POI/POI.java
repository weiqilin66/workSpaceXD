package com.readwrite.POI;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class POI {


    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 关键字this只能在方法内部使用，表示对当前对象的引用。

     */
    public   List<List<Object>> importExcel(InputStream in,String fileName) throws Exception{
        List<List<Object>> list = null;

        //创建Excel工作薄,用"this.成员方法名"访问成员方法
        Workbook work = this.getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}
            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <=sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){
                    continue;
                }

                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int k = row.getFirstCellNum(); k <row.getLastCellNum(); k++) {
                    cell = row.getCell(k);
                    if(cell==null){continue;}

                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        //System.out.println(list);
        return list;

    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        //System.out.println("文件格式:"+fileType);
        if(excel2003L.equals(fileType) || excel2003L.toUpperCase().equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType) || excel2007U.toUpperCase().equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }


    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }


    //导出
    public void outPutExcel() throws Exception {
        // 每次写1000行数据，就刷新数据出缓存
        SXSSFWorkbook wb = new SXSSFWorkbook(1000); // keep 100 rows in memory,
        Sheet sh = wb.createSheet();
        // 这个是业务数据
        List tmps =new ArrayList();//null
        String[] titles = { "编号", "标题" };
        Row row = sh.createRow(0);
        // 第一行设置标题
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            Cell cell1 = row.createCell(i);
            cell1.setCellValue(title);
        }

        // 导出数据
        for (int rowNum = 0; rowNum < tmps.size(); rowNum++) {

            Row rowData = sh.createRow(rowNum + 1);
            // TbClass 这个是我的业务类，这个是根据业务来进行填写数据
            //TbClass tmp = tmps.get(rowNum);
            // 第一列
            Cell cellDataA = rowData.createCell(0);
            //cellDataA.setCellValue(tmp.getcId());
            // 第二列
            Cell cellDataB = rowData.createCell(1);
            //cellDataB.setCellValue(tmp.getcName());
        }

        String fileName = "文件名称.xlsx";
        //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        //wb.write(response.getOutputStream());
        wb.close();
    }


}

