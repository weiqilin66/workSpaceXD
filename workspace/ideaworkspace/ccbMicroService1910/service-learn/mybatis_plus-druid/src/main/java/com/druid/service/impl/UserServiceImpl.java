package com.druid.service.impl;

import com.druid.entity.User;
import com.druid.mapper.UserMapper;
import com.druid.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LinWeiQi
 * @since 2020-01-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
