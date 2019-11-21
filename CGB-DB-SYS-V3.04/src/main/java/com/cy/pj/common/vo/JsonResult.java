package com.cy.pj.common.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * VO对象
   *  设计此对象的目的是封装控制层要响应到客户端的数据.
 */
@Data
@NoArgsConstructor
public class JsonResult implements Serializable{
	private static final long serialVersionUID = 677048178703375661L;
    /**状态码(服务端响应到客户端的状态码)*/
	private int state=1;//1 代表ok,0代表error
	/**表示状态码对象的状态信息*/
	private String message="ok";
	/**表示响应到客户端的具体数据*/
	private Object data;
	public JsonResult(String message){
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	}
	public JsonResult(Throwable e) {
		this.state=0;//表示异常
		this.message=e.getMessage();
	}
}
