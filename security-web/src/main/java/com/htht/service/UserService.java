package com.htht.service;

import com.htht.entity.SysUser;

/**
 * Created by admin on 2019/11/29.
 */
public interface UserService {
    SysUser getByName(String userName);
}
