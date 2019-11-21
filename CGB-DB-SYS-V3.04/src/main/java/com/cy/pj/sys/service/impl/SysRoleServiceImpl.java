package com.cy.pj.sys.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.config.PageProperties;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.sys.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private PageProperties pageProperties;
	
	@Override
	public List<CheckBox> findObjects() {
		return sysRoleDao.findObjects();
	}
	
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		//1.参数校验
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		//2.查询数据
		SysRoleMenuVo rm=sysRoleDao.findObjectById(id);
		//3.结果校验并返回
		if(rm==null)
			throw new ServiceException("记录可能已经不存在");
		return rm;
	}
	
	@Override
	public int saveObject(SysRole entity,
			Integer[] menuIds) {
		//1.参数有效性校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new IllegalArgumentException("需要为角色分配权限");
		//2.保存角色自身信息
		int rows=sysRoleDao.insertObject(entity);
		//3.保存角色和菜单关系数据
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//4.返回结果
		return rows;
	}
	@Override
	public int updateObject(SysRole entity,
			Integer[] menuIds) {
		//1.参数有效性校验
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色名不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new IllegalArgumentException("需要为角色分配权限");
		//2.更新角色自身信息
		int rows=sysRoleDao.updateObject(entity);
		if(rows==0)
		    throw new ServiceException("记录可能已经不存在");
		//3.保存角色和菜单关系数据
		//3.1删除原有关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		//3.2添加新的关系数据
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		//4.返回结果
		return rows;
	}
	
	@Override
	public int deleteObject(Integer id) {
		//1.参数校验
		if(id==null||id<1)
			throw new IllegalArgumentException("id值无效");
		//2.删除角色关系数据
		//2.1删除角色菜单关系数据
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		//2.2删除角色用户关系数据
		sysUserRoleDao.deleteObjectsByRoleId(id);
		//3.删除自身信息并返回结果
		int rows=sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		//1.参数有效性校验
		if(pageCurrent==null||pageCurrent<1)
			throw new IllegalArgumentException("页码值不正确");
		//2.基于条件查询总记录数
		int rowCount=sysRoleDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.查询当前页记录
		int pageSize=pageProperties.getPageSize();
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
		sysRoleDao.findPageObjects(name,startIndex, pageSize);
		//4.封装数据被返回
		return new PageObject<>(records, rowCount, pageCurrent, pageSize);
	}
}




