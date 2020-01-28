package com.lwq.hr.mapper;

import com.lwq.hr.entity.Hr;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-28
 */
public interface HrMapper extends BaseMapper<Hr> {

    @Select("select * from hr where username = #{username}")
    Hr getHr(String username);
}
