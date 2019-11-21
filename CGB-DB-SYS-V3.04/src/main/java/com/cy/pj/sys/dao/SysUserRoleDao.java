package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
  * 基于此DAO操作用户和角色关系数据
 */
@Mapper
public interface SysUserRoleDao {
	   /**
	        *  查找用户对应的角色id
	    * @param userId
	    * @return
	    */
	   @Select("select role_id from sys_user_roles where user_id=#{userId}")
	   List<Integer> findRoleIdsByUserId(Integer userId);
	   /**
	    * 保存用户和角色关系数据
	    * @param userId
	    * @param roleIds
	    * @return
	    */
	   int insertObjects(
			     @Param("userId")Integer userId,
			     @Param("roleIds")Integer[] roleIds);
	   
	   @Delete("delete from sys_user_roles where user_id=#{userId}")
	   int deleteObjectsByUserId(Integer userId);
	
	   @Delete("delete from sys_user_roles where role_id=#{roleId}")
	   int deleteObjectsByRoleId(Integer roleId);
}










