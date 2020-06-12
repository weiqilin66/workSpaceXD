package com.lwq.hr;

import com.lwq.hr.entity.MyStock;
import com.lwq.hr.mapper.GoodsMapper;
import com.lwq.hr.mapper.MyStockMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class HrTest {

    @Resource
    MyStockMapper stockMapper;
    @Resource
    GoodsMapper goodsMapper;
    @Test
    void t1(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date());
        System.out.println(now);

    };
    @Test
    void t2(){
        List<MyStock> all = stockMapper.getAll();
        System.out.println(all);
    }

}

