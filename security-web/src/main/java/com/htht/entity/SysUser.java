package com.htht.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by admin on 2019/11/29.
 */
@Data
public class SysUser implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String password;

}
