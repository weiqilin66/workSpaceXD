package com.lwq.hr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwq.hr.entity.SecondShopForMax;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-05-22
 */
public interface SecondShopForMaxMapper extends BaseMapper<SecondShopForMax> {

    @Select("select * from second_shop_for_max")
    List<SecondShopForMax> selectAll();
}
