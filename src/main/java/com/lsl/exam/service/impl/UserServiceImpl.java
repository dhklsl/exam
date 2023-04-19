package com.lsl.exam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsl.exam.entity.TabUser;
import com.lsl.exam.mapper.UserMapper;
import com.lsl.exam.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, TabUser> implements IUserService {
}
