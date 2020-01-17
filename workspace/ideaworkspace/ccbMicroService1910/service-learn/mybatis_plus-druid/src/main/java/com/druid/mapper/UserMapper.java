package com.druid.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.druid.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-17
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select *from user")
    List<User> getUser();
}
