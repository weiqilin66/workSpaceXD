package com.lwq.hr.controller.sta;

import com.lwq.hr.entity.Goods;
import com.lwq.hr.mapper.GoodsMapper;
import lwq.returnbean.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/statistics/chart/")
public class ChartController {

    @Resource
    GoodsMapper goodsMapper;

    /**
     * @TODO   查当天最新爬取数据的
     * 最高价与最低价 及差价(价格区间在100-800)
     * 100以下800以上额外统计
     * @date   2020/3/11
     */
    @GetMapping("/maxAndMin")
    public RespBean getMaxAndMin(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date());
        List<Goods> list = goodsMapper.maxAndMin(now);
        return RespBean.ok();
    }

}
