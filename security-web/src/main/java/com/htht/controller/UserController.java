package com.htht.controller;

import com.htht.entity.SysUser;
import com.htht.service.UserService;
import com.htht.test.BBBB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2019/11/29.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public String me(String userName){
        BBBB a = new BBBB();
        SysUser user = userService.getByName(userName);
        return  user.toString();
    }
}
