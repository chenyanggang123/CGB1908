package com.cy.pj.sys.dao.test;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.vo.SysUserDeptVo;

@SpringBootTest
public class SysUserDaoTests {
	@Autowired
	private SysUserDao sysUserDao;
	@Test
	public void testFindPageObjects() {
		List<SysUserDeptVo> list=
		sysUserDao.findPageObjects("a",0, 3);
		for(SysUserDeptVo vo:list) {
			System.out.println(vo);
		}
		//list.forEach(a->System.out.println(a));
	}//jdk8 lamda
}



