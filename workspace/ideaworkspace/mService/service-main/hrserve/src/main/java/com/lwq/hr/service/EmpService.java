package com.lwq.hr.service;

import com.lwq.hr.entity.Employee;
import com.lwq.hr.mapper.EmployeeMapper;
import lwq.result.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public RespPageBean getByPage(int page,int size){
        RespPageBean respPageBean = new RespPageBean();
        if (page!=0 && size!=0) {
            page = (page-1)*size;
        }
        long total = employeeMapper.getTotal(null,null);
        respPageBean.setTotal(total);
        List<Employee> list = employeeMapper.getEmployeeByPage(page, size,null,null);
        respPageBean.setData(list);
        return respPageBean;
    }
}
