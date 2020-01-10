/**
 * 
 */
package com.htht.security.browser.core.properties;

/**
 * 浏览器的配置项
 */
public class BrowserProperties {
	
	private String loginPage = "/imooc-signIn.html";
	
	private LoginType loginType = LoginType.JSON;

	/**
	 * 记住我的时间(1小时)
	 */
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
}
