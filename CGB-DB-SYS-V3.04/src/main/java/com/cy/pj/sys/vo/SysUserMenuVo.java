package com.cy.pj.sys.vo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
/**借助此对象封装用户菜单信息*/
@Data
public class SysUserMenuVo implements Serializable{
	private static final long serialVersionUID = -6532648335943768546L;
	/**菜单id*/
	private Integer id;
	/**菜单名称*/
	private String name;
	/**菜单url*/
	private String url;
	/**当前菜单的子菜单*/
	private List<SysUserMenuVo> childMenus;
}
