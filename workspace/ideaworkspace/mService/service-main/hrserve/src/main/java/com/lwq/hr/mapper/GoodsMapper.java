package com.lwq.hr.mapper;

import com.lwq.hr.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    List<Goods> maxAndMin(String date);
}
