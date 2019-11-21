package com.cy.pj.sys.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	@Select("select * from sys_users where username=#{username}")
	SysUser findUserByUserName(String username);
	
	@Select("select count(*) from sys_users where ${columnName}=#{columnValue}")
	int isExist(String columnName,String columnValue);
	
	/**
	 * 更新用户自身信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	
	/**
	 * 基于id查找用户以及用户对应的部门信息
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	/**
	 * 保存用户自身信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);
	/**
	  *  禁用或启用用户状态
	 * @param id
	 * @param valid
	 * @param modifiedUser
	 * @return
	 */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);

	   /**
	        * 基于条件查询用户总记录数
	    * @param username
	    * @return
	    */
	   int getRowCount(@Param("username")String username);
	   /**
	        * 基于条件查询当前页要呈现的记录
	    * @param username
	    * @param startIndex
	    * @param pageSize
	    * @return
	    */
	   List<SysUserDeptVo> findPageObjects(
			   @Param("username")String username,
			   @Param("startIndex")Integer startIndex,
			   @Param("pageSize")Integer pageSize);
}
