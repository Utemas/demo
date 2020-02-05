package com.demo.demo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig{

    //创建ShiroFilterFactorBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加Shiro内置过滤器
        // Shiro内置过滤器，可以实现权限相关的拦截器
        /*
            常用过滤器
            anon: 无需认证（登录）可以访问
            authc: 必须认证才可以访问
            user: 如果使用rememberMe的功能可以直接访问
            perms: 该资源必须得到资源权限才可以访问
            role: 该资源必须得到角色权限才可以访问

        */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        // filterMap.put("/add","authc");
        // filterMap.put("/update","authc");
        filterMap.put("/index","anon");
        filterMap.put("/login","anon");
        filterMap.put("/user/information","authc");
        //授权过滤器
        //注意：当前授权拦截后，shiro会自动跳转到未授权的页面
        filterMap.put("/writing","perms[user:writing]");
        
        //设置未授权提示页面

        //修改调整的登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        
        

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    //创建DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建Realm
    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}