package com.lwq.hr.mapper;

import com.lwq.hr.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-28
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select * from role where id in(select rid from hr_role where hrId=#{id} )")
    List<Role> queryRoles(Integer id);
}
