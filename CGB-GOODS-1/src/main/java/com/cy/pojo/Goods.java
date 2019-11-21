package com.cy.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
@Data
public class Goods {
	private Long id;
	private String name;
	private String  remark;
	private Date createdTime;
	
}
