package com.cy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pojo.Goods;
import com.cy.service.GoodsService;

@Controller
@RequestMapping("/goods/")
public class  GoodsController {
	@Autowired
	private GoodsService goodsService;
	@RequestMapping("doFindGoods")
	public String doFindGoods(Model model) {
		List<Goods> list = goodsService.findGoods();
		//系统底层将list对象存储在map集合，并将此对象存储到请求作用域
		model.addAttribute("goods",list);
		System.out.println(list);
		return "goods";
	}
	//thymeleaf是一个服务端的模板引擎（以html为呈现
	//thymeleaf 提供了一些自定义的标签，属性操作服务站
	@RequestMapping("doGoodsUI")
	public String doGoodsUI() {
		return "goods-ajax";
	}
	
	
	@RequestMapping("doFindObjects")
	@ResponseBody
	public List<Goods> doFindObjects(){
		return goodsService.findGoods();
	}//@ResponseBody告诉spring mvc将返回值转换为JSON格式的字符串。
}
