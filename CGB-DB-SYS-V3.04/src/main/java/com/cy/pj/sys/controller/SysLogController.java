package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
/**
 *   日志控制层对象
 *   核心业务
 * 1)请求数据处理(请求路径,请求方式,请求参数)
 * 2)响应数据处理(数据封装,数据转换)
 */
//@Controller
//@ResponseBody
@RestController//==@Controller+@ResponseBody
@RequestMapping("/log/")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping(value="doDeleteObjects",method = RequestMethod.POST)
	public JsonResult doDeleteObjects(
			Integer...ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
	/**
	 * 基于请求参数执行日志信息的获取
	 * @param username 用户名
	 * @param pageCurrent 当前页码值
	 * @return 返回值为json格式的字符串
	 * 说明:假如对方法参数有更高要求可以
	 * 使用@RequestParam注解对参数进行描述
	 */
	//@RequestMapping(value="doFindPageObjects",
		//	method = RequestMethod.GET)
	@GetMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
		    String username,
			Integer pageCurrent) throws JsonProcessingException {
	  	    System.out.println("======");
		    PageObject<SysLog> pageObject=
	  	    sysLogService.findPageObjects(username, pageCurrent);
		return  new JsonResult(pageObject);
	}//spring mvc 会借助jackson api将结果转换为json格式字符串
}








