package com.htht.service.impl;

import com.htht.entity.SysUser;
import com.htht.mapper.SysUserMapper;
import com.htht.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2019/11/29.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByName(String userName) {
        return sysUserMapper.getByName(userName);
    }
}
