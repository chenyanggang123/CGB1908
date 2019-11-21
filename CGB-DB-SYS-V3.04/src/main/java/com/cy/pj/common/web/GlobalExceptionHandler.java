package com.cy.pj.common.web;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cy.pj.common.vo.JsonResult;
/**
 * @ControllerAdvice 注解描述的类为异常处理类,
  *  此类中可以定义多个异常处理方法
 */
//@ControllerAdvice
//@ResponseBody
@RestControllerAdvice //==@ControllerAdvice+@ResponseBody
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ShiroException.class)
	//@ResponseBody
	public JsonResult doHandleShiroException(
			ShiroException e) {
		JsonResult r=new JsonResult();
		r.setState(0);
		if(e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		}else if(e instanceof LockedAccountException) {
			r.setMessage("账户已被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		}else if(e instanceof AuthorizationException) {
			r.setMessage("没有此操作权限");
		}else {
			r.setMessage("系统维护中");
		}
		e.printStackTrace();
		return r;
	}

	/**
	 * @ExceptionHandler 用于描述异常处理方法,注解
	   * 内部的异常类型,为方法能处理的异常类型.
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public JsonResult doHandleRuntimeException(
			RuntimeException e) {
		//输出异常栈中的信息
		e.printStackTrace();
		//封装异常信息
		return new JsonResult(e);
	}
}
