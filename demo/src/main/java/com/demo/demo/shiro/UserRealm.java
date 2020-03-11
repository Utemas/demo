
package com.demo.demo.shiro;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * UserRealm
 */
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		
		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Customer customer = (Customer)subject.getPrincipal();
		//添加资源的授权字符串
		info.addStringPermission(customer.getCustomer_identify());
		

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		//假设数据库的用户和密码
		
		//编写shiro判断逻辑，判断用户名和密码。
		//1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String customer_identify = token.getUsername().substring(0, 2);// 用户身份代码
        String customer_id = token.getUsername().substring(2);// 用户id
		
		Customer customer = customerMapper.getCustomerByStid(customer_identify, customer_id);
		if(customer == null){
			//用户名不存在
			return null;//Shiro底层会抛出UnknownAccountException
		}
		//2.判断密码
		return new SimpleAuthenticationInfo(customer,customer.getCustomer_password(),"");
	}

}