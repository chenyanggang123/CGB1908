package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringShiroConfig {
	
      @Bean
	  public SessionManager sessionManager() {
		  DefaultWebSessionManager sessionManager=
				new DefaultWebSessionManager();
		  sessionManager.setGlobalSessionTimeout(60*60*1000);
		  return sessionManager;
	  }
	  @Bean
	  public RememberMeManager cookieRememberMeManager() {
		  CookieRememberMeManager rManager=new CookieRememberMeManager();
		  SimpleCookie cookie=new SimpleCookie("rememberMe");
		  cookie.setMaxAge(10*60);
		  rManager.setCookie(cookie);
		  return rManager;
	  }
	
	  /**
	      * 配置shiro框架内置缓存对象
	   *@return
	   */
	  @Bean
	  public CacheManager shiroCacheManager() {
		  return new MemoryConstrainedCacheManager();
	  }
	
      /**
       * @Bean 注解描述的方法，表示方法的返回值
             * 对象要交给Spring管理，默认key为方法名
       * SecurityManager对象是Shiro框架的核心
       * 注意：此对象为org.apache.shiro.mgt
       * 包中的对象。
       * @return
       */
	  @Bean //<bean id="" class=""/>
	  public SecurityManager securityManager(
			  Realm realm,
			  CacheManager cacheManager,
			  RememberMeManager rememberMeManager,
			  SessionManager sessionManager
			  ) {
		  DefaultWebSecurityManager sManager=
				 new DefaultWebSecurityManager();
		  sManager.setRealm(realm);
		  sManager.setCacheManager(cacheManager);
		  sManager.setRememberMeManager(rememberMeManager);
		  sManager.setSessionManager(sessionManager);
		  return sManager;
	  }
	  @Bean
	  public ShiroFilterFactoryBean shiroFilterFactory(
			  SecurityManager securityManager) {
		  ShiroFilterFactoryBean fBean=
				  new ShiroFilterFactoryBean();
		  fBean.setSecurityManager(securityManager);
		  fBean.setLoginUrl("/doLoginUI");
		  LinkedHashMap<String,String> filterMap=new LinkedHashMap<>();
		  //设置允许匿名访问的资源
		  filterMap.put("/bower_components/**","anon");
		  filterMap.put("/build/**","anon");
		  filterMap.put("/dist/**","anon");
		  filterMap.put("/plugins/**","anon");
		  filterMap.put("/user/doLogin","anon");
		  filterMap.put("/doLogout","logout");
          //设置必须认证才可访问的资源(需要认证访问的资源，需要放在map最后)
		  filterMap.put("/**", "user");//authc
		  fBean.setFilterChainDefinitionMap(filterMap);
		  return fBean;
	  }
	  //授权配置
	  /**初始化Advisor对象，此对象关联了一个切入点,例如你方法上
	      *是否有@RequiresPermissions注解*/
	  @Bean
	  public AuthorizationAttributeSourceAdvisor 
	  newAuthorizationAttributeSourceAdvisor(
	  	    		SecurityManager securityManager) {
	  		        AuthorizationAttributeSourceAdvisor advisor=
	  				new AuthorizationAttributeSourceAdvisor();
	    advisor.setSecurityManager(securityManager);
	  	return advisor;
	  }

}




