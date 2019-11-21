package com.cy.pj.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	//@Autowired
	private SysMenuDao sysMenuDao;
	//@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@CacheEvict(value ="menuCache",allEntries = true)
	@Override
	public int updateObject(SysMenu entity) {
		//1.参数有效性校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		if(StringUtils.isEmpty(entity.getPermission()))
			throw new IllegalArgumentException("授权标识不能为空");
		//....
		//2.将对象保存到数据库
		int rows=sysMenuDao.updateObject(entity);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		//3.返回结果
		return rows;
	}
	@CacheEvict(value ="menuCache",allEntries = true)
	@Override
	public int saveObject(SysMenu entity) {
		//1.参数有效性校验
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		if(StringUtils.isEmpty(entity.getPermission()))
			throw new IllegalArgumentException("授权标识不能为空");
		//....
		//2.将对象保存到数据库
		int rows=sysMenuDao.insertObject(entity);
		//3.返回结果
		return rows;
	}
	
	@Autowired
	public SysMenuServiceImpl(SysMenuDao sysMenuDao,SysRoleMenuDao sysRoleMenuDao) {
		this.sysMenuDao=sysMenuDao;
		this.sysRoleMenuDao=sysRoleMenuDao;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@RequiresPermissions("sys:menu:delete")
	@Transactional
	@Override
	public int deleteObject(Integer id) {
		//1.参数有效性校验
		if(id==null||id<1)
			throw new IllegalArgumentException("参数无效");
		//2.检查当前菜单是否有子菜单
		int rowCount=sysMenuDao.getChildCount(id);
		if(rowCount>0)
			throw new ServiceException("请先删除子菜单");
		//3.删除关系数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//4.删除自身信息并返回结果
		int rows=sysMenuDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	@Cacheable(value = "menuCache")//menuCache为cache对象的名字
	@Transactional(readOnly = true)
	@Override
	public List<Map<String, Object>> findObjects() {
	    System.out.println("==MenuServiceImpl.findObjects==");
		List<Map<String,Object>> list=
	    sysMenuDao.findObjects();
	    if(list==null||list.size()==0)
	    	throw new ServiceException("没有对应的记录");
		return list;
	}
}
