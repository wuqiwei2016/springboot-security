package com.htht.security.browser.core.validate.code;


import org.springframework.web.context.request.ServletWebRequest;

/**
 * 自定义验证码生成器
 */
public interface ValidateCodeGenerator {
    ImageCode generate(ServletWebRequest servletWebRequest);
}
