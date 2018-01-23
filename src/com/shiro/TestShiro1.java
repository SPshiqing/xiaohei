package com.shiro;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
public class TestShiro1 {
	
	@Test
	public void shiroLogin() {
		//1.创建安全管理工厂
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory("classpath:shiro.ini");
		//2.通过工厂得到一个安全管理实例
		org.apache.shiro.mgt.SecurityManager securityManager = 
				factory.getInstance();
		//3.把实例设置给安全工具
		SecurityUtils.setSecurityManager(securityManager);
		//4.通过安全工具创建一个用户
		Subject subject = SecurityUtils.getSubject();
		//5.创建一个用户名和密码对象
		UsernamePasswordToken token = 
				new UsernamePasswordToken("wangli","123456");
		//6.登录
		try {
			subject.login(token);
			System.out.println("登录成功");
			//7.如果登录成功后，注销
			subject.logout();
		} catch (AuthenticationException e) {
			System.out.println("登录失败");
		}
	}
}
