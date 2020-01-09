package com.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-07
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> getAll();
}
