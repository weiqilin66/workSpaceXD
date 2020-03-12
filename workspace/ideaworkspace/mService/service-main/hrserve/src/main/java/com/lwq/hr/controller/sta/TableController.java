package com.lwq.hr.controller.sta;

import com.lwq.hr.entity.Goods;
import com.lwq.hr.mapper.GoodsMapper;
import lwq.returnbean.RespPageBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * 数据清理
 * 根据宝贝分类查出价格最高和价格最低 综合分析出自己商品价格
 * 定位可信任(销量)店铺 价格差 做转卖
 * 分析价格走势
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/statistics/table")
public class TableController {

    @Resource
    GoodsMapper goodsMapper;

    @GetMapping("/")
    public RespPageBean getTableData(){
        RespPageBean respPageBean = new RespPageBean();
        long total = goodsMapper.selectAll();
        respPageBean.setTotal(total);
        HashMap<String, Object> columnMap = new HashMap<>();
        List<Goods> list = goodsMapper.selectByMap(columnMap);
        respPageBean.setData(list);
        return respPageBean;
    }

}
