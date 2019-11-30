package com.htht.mapper;

import com.htht.entity.SysUser;

/**
 * Created by admin on 2019/11/29.
 */
public interface SysUserMapper {
    SysUser getByName(String userName);
}
