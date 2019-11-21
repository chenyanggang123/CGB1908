package com.cy.pj.sys.vo;

import java.io.Serializable;
import java.util.List;

import com.cy.pj.sys.entity.SysMenu;

import lombok.Data;
/**VO*/
@Data
public class SysRoleMenuVo implements Serializable{
	private static final long serialVersionUID = -7213694248989299601L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
	/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
}
