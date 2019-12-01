package com.htht.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder  passwordEncoder;

    /**
     * 自定义用户认证逻辑(这个目前不会直接操作数据库，我会在程序中直接定义用户，如果想要操作数据库查询用户，我会在后期添加进来。)
     * @param
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名：" + username);
        //获取用户名查找用户信息
        //security提供的user类里面还可以设置用户是否过期，是否失效等，可以自己根据业务去实现。
/*
        return new User(username,"123456",AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
*/
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是:"+password);

        return new User(username, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));    }
}
