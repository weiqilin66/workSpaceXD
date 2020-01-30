package com.lwq.hr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lwq.hr.entity.Menu;
import com.lwq.hr.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class HrserveApplicationTests {

    @Resource
    MenuMapper menuMapper;
    @Test
    void contextLoads() throws JsonProcessingException {

        List<Menu> menu = menuMapper.getMenuById(5);
        System.out.println(new ObjectMapper().writeValueAsString(menu));
        System.out.println(menu.size());
    };

}
