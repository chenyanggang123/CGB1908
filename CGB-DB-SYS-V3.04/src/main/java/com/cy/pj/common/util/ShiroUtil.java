package com.cy.pj.common.util;

import org.apache.shiro.SecurityUtils;

import com.cy.pj.sys.entity.SysUser;

public class ShiroUtil {

	public static String getUsername() {
		return getUser().getUsername();
	}
	public static SysUser getUser() {
		return (SysUser)
		 SecurityUtils.getSubject().getPrincipal();
	}
}
