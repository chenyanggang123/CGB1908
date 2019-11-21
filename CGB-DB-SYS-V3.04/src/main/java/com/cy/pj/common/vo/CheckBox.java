package com.cy.pj.common.vo;
import java.io.Serializable;
import lombok.Data;
/**
 * 值对象
 * @author Administrator
 */
@Data
public class CheckBox  implements Serializable{
	private static final long serialVersionUID = 5065823170856122977L;
	private Integer id;
	private String name;
}
