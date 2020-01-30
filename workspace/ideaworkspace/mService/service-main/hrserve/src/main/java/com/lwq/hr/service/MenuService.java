package com.lwq.hr.service;

import com.lwq.hr.entity.Hr;
import com.lwq.hr.entity.Menu;
import com.lwq.hr.mapper.MenuMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Service
public class MenuService {

    @Resource
    MenuMapper menuMapper;

    public List<Menu> getMenuById(){
        // SecurityContextHolder中取出登录的用户ID
        return menuMapper.getMenuById(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
