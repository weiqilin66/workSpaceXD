package com.lwq.hr.mapper;

import com.lwq.hr.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lwq.hr.entity.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-28
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuById(int id);

    Menu t1(int id);

    List<Role> getAllMenuRoles(String url);

    List<Menu> getMenuWithRoles();
}
