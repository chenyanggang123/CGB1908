package com.cy.pj.sys.dao.test;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
@SpringBootTest
public class SysLogDaoTests {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Test
	public void testDeleteObjects() {
		int rows=sysLogDao.deleteObjects(31,32,33);
		System.out.println("delete.rows="+rows);
	}
	
	@Test
	public void testGetRowCount() {
		int rows=
		sysLogDao.getRowCount("admin");
		System.out.println("rows="+rows);
	}
	@Test
	public void testFindPageObjects() {
		List<SysLog> list=
		sysLogDao.findPageObjects("admin",
				0, 3);
		System.out.println(list.size());
	}
}






