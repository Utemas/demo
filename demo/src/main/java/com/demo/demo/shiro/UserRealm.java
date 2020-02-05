
package com.demo.demo.shiro;

import com.demo.demo.Mapper.CustomerMapper;
import com.demo.demo.po.Customer;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
        // TODO Auto-generated method stub
        System.out.println("执行认证逻辑 1");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        // TODO Auto-generated method stub
		//假设数据库的用户和密码
		
		//编写shiro判断逻辑，判断用户名和密码。
		//1.判断用户名
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		String customer_identify = token.getUsername().substring(0, 2);// 用户身份代码
        String customer_id = token.getUsername().substring(2);// 用户id
		
		Customer customer = customerMapper.getCustomerById(customer_identify,customer_id);
		if(customer == null){
			//用户名不存在
			return null;//Shiro底层会抛出UnknownAccountException
		}
		//2.判断密码
		return new SimpleAuthenticationInfo("",customer.getCustomer_password(),"");
	}

}