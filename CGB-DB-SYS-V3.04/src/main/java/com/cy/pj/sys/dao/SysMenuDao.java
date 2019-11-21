package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.vo.SysUserMenuVo;
/**
 * @CacheNamespaceRef 注解表示此接口中的查询操作
 * 要使用二级缓存,其二级缓存的配置参考name属性对应
 * 的命名空间配置.
 */
@Mapper
@CacheNamespaceRef(name="com.cy.pj.sys.dao.SysMenuDao")
public interface SysMenuDao {
	/**
	 * 查询用户菜单(默认假设是最高权限用户的所有菜单)
	 */
	public List<SysUserMenuVo> findUserMenus();
	/**
	  * 基于多个菜单id,查找权限标识
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);

	
	/**
	 *  将菜单信息更新到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	 /**
	    *  将菜单信息持久化到数据库
	  * @param entity
	  * @return
	  */
	  int insertObject(SysMenu entity);
	
	  @Select("select id,name,parentId from sys_menus")
	  List<Node> findZtreeMenuNodes();
	  /***
	      * 基于id统计子菜单的个数
	   * @param id
	   * @return
	   */
	  @Select("select count(*) from sys_menus where parentId=#{id}")
	  int getChildCount(Integer id);
	  /**
	   * 基于id删除菜单自身信息
	   * @param id
	   * @return
	   */
	  @Delete("delete from sys_menus where id=#{id}")
	  int deleteObject(Integer id);
	  
	
	  @Select("select * from sys_menus where id=#{id}")
	  Map<String,Object> findById(Integer id);
	  /**
	   * 查询所有菜单以及上级菜单的名称,
	   * 一行记录映射为一个map对象
	   * @return
	   */
	  List<Map<String,Object>> findObjects();
}




