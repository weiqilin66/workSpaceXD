package com.lwq.hr;

import com.lwq.hr.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class HrTest {

    @Resource
    GoodsMapper goodsMapper;
    @Test
    void t1(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date());
        System.out.println(now);

    };

}

