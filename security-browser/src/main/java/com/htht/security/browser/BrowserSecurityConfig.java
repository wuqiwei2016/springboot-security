package com.htht.security.browser;


import com.htht.security.browser.core.properties.SecurityProperties;
import com.htht.security.browser.authentication.HthtAuthenctiationFailureHandler;
import com.htht.security.browser.authentication.HthtAuthenticationSuccessHandler;
import com.htht.security.browser.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private HthtAuthenticationSuccessHandler hthtAuthenticationSuccessHandler;
    @Autowired
    private HthtAuthenctiationFailureHandler hthtAuthenctiationFailureHandler;
    //配置数据源,页面添加remberMe功能
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @description: 配置remberMe功能 生成表
     * @author: wqw
     * @time: 2020/1/9 2:57 下午
     */
   @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(true);//项目第一次启动会创建表,表创建成功之后需要注释掉这块代码
        return tokenRepository;
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(hthtAuthenctiationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();
        /**
         * 这段代码段意思是任何请求都需要拦截,这种是表单提交
         * http.formLogin()
         *                 .and()
         *                 .authorizeRequests()
         *                 .anyRequest()
         *                 .authenticated();
         */
/*
        http.httpBasic()   //basic登陆
*/
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require") //这块是跳转到方法上了controller
                .loginProcessingUrl("/authentication/form") //这个url就是登录页面的表单提交url
                .successHandler(hthtAuthenticationSuccessHandler) //登录成功之后的逻辑
                .failureHandler(hthtAuthenctiationFailureHandler) //失败处理逻辑
                .and()
                 //记住我功能
                .rememberMe()
                  .tokenRepository(persistentTokenRepository())
                  .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                  .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()//配置不需要验证的url
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()  //加这个匹配器，是为了跳转登录页面的时候，防止一直重定向
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }
}
