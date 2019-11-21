package com.cy.pj.sys.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysLog;

import lombok.Delegate;
/**
 * DAO (数据访问对象)
 * 1)此对象的实现类由mybatis框架创建
 * 2)此对象的实现类中会自动注入SqlSessionFactory对象
 */
@Mapper
public interface SysLogDao {//com.cy.pj.sys.dao.SysLogDao
	
	int insertObject(SysLog entity);
	/**
	 * 基于日志记录id执行删除业务
	 * @param ids (多个记录id)
	 * @return 删除行数
	 */
	int deleteObjects(@Param("ids")Integer...ids);
    /**
     * 基于条件统计用户行为日志
     * @param username
     * @return
     */
	int getRowCount(@Param("username")String username);
	/**
	 * 基于条件username,从指定位置startIndex,查询pageSize条数据
	 * @param username 用户名
	 * @param startIndex 当前页起始位置
	 * @param pageSize 页面大小(每页最多显示多少条记录)
	 * @return 查询到的结果,一行记录对应一个SysLog对象
	 */
	List<SysLog> findPageObjects(
			@Param("username")String username,//param1
			@Param("startIndex")Integer startIndex,//param2
			@Param("pageSize")Integer pageSize);//param3
	
}





