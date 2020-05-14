package com.lwq.hr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwq.hr.entity.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-03-09
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    @Select("select count(*) from goods")
    long selectAll();

    List<Goods> byTitle(String shop, String title, List<String> days, String condition);

    List<Goods> getMax(String now, String goodName, String condition);

    List<Goods> getMin(String now, String goodName, String condition);

    List<Goods> selTotal(String now);
}
