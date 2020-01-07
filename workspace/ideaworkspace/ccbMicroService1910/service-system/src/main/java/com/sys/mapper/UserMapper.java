package com.sys.mapper;

import com.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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
    List findAll(User user);
}
