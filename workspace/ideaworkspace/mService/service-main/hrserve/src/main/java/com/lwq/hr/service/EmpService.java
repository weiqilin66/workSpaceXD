package com.lwq.hr.service;

import com.lwq.hr.entity.Employee;
import com.lwq.hr.mapper.EmployeeMapper;
import lwq.returnbean.RespPageBean;
import lwq.utils.POIUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: select *from table limit 5,10  查询第6-15条数据
 * @author: LinWeiQi
 */
@Service
public class EmpService {

    @Resource
    EmployeeMapper employeeMapper;

    // 分页查询
    public RespPageBean getByPage(int page,int size,String keyWord,int id){
        RespPageBean respPageBean = new RespPageBean();
        if (page!=0 && size!=0) {
            page = (page-1)*size;
        }
        long total = employeeMapper.getTotal(null,null);
        respPageBean.setTotal(total);
        Employee employee = new Employee();
        employee.setName(keyWord);
        if(id != -1){
            employee.setId(id);
        }
        List<Employee> list = employeeMapper.getEmployeeByPage(page, size,employee,null);
        respPageBean.setData(list);
        return respPageBean;
    }
    //导出
    public ResponseEntity<byte[]> exportExcel(){
        HashMap<Integer, CellStyle> lineStyleMap = new HashMap<>();
        HashMap<Integer, Integer> columnWidthMap = new HashMap<>();
        ArrayList<List<String>> headLists = new ArrayList<>();
        ArrayList<List<?>> dataList = new ArrayList<>();
        List<Employee> eli = new ArrayList<>();
        eli = employeeMapper.getEmployeeByPage(null,null,null,null);
        dataList.add(eli);
        Workbook workbook = POIUtils.MyExportExcel2003(lineStyleMap, columnWidthMap, headLists, dataList);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment",
                    new String("员工表".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)
            );
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(bos.toByteArray(),headers, HttpStatus.CREATED);
    }
}
