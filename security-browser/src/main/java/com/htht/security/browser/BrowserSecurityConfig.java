package com.htht.security.browser;


import com.htht.core.properties.SecurityProperties;
import com.htht.security.browser.authentication.HthtAuthenctiationFailureHandler;
import com.htht.security.browser.authentication.HthtAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private HthtAuthenticationSuccessHandler hthtAuthenticationSuccessHandler;
    @Autowired
    private HthtAuthenctiationFailureHandler hthtAuthenctiationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
        http.formLogin()
                .loginPage("/authentication/require") //这块是跳转到方法上了controller
                .loginProcessingUrl("/authentication/form") //这个url就是登录页面的表单提交url
                .successHandler(hthtAuthenticationSuccessHandler) //登录成功之后的逻辑
                .failureHandler(hthtAuthenctiationFailureHandler) //失败处理逻辑
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()  //加这个匹配器，是为了跳转登录页面的时候，防止一直重定向
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

    }
}
