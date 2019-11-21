package com.cy.pj.sys.vo;

import java.io.Serializable;
import java.util.Date;

import com.cy.pj.sys.entity.SysDept;

import lombok.Data;

/**
  *  封装查询结果的vo对象
 */
@Data
public class SysUserDeptVo implements Serializable{
	private static final long serialVersionUID = 1258517159478776012L;
	private Integer id;
	private String username;
	private String email;
	private String mobile;
	private Integer valid=1;
	private SysDept sysDept; //private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
