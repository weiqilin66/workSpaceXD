package com.lwq.springsecuritymybatis.mapper;

import com.lwq.springsecuritymybatis.entity.Role;
import com.lwq.springsecuritymybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-19
 */
@Mapper
public interface UserMapper {

    User loadUserByUsername(String username);

    List<Role> getUserRoleById(int id);
}
