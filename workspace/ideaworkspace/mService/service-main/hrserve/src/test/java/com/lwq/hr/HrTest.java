package com.lwq.hr;

import com.lwq.hr.entity.Menu;
import com.lwq.hr.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class HrTest {

    @Resource
    MenuMapper menuMapper;
    @Test
    void t1(){
        List<Menu> menuById = menuMapper.getMenuById(5);
        menuById.stream().forEach(System.out::println);
    };

}
