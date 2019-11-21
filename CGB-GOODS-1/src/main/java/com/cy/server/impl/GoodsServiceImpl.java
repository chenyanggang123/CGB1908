package com.cy.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.dao.GoodsDao;
import com.cy.pojo.Goods;
import com.cy.service.GoodsService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsdao;
	@Override
	public List<Goods> findGoods() {
		log.info("start:"+System.currentTimeMillis());
		List<Goods> list = goodsdao.findGoods();
		log.info("end:"+System.currentTimeMillis());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		return list;
	}

}
