package com.lwq.hr.controller.emp;

import com.lwq.hr.service.EmpService;
import lwq.result.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: emp分页查询,导入导出
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {

    @Autowired
    EmpService empService;
    /**
     * @TODO    分页查询
     * @param  RequestParam 指定请求参数的相关属性
     * @example select *from table limit 5,10  查询第6-15条数据
     * @date   2020/2/13
     */
    @GetMapping("/")
    public RespPageBean getEmpPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue ="10") int size){
        return empService.getByPage(page,size);
    }
}
