package com.cy.pj.sys.dao.test;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.vo.SysUserMenuVo;

@SpringBootTest
public class SysMenuDaoTests {
	  @Autowired
	  private SysMenuDao sysMenuDao;
	  @Autowired
	  private SqlSessionFactory sessionFactory;
	  
	  @Test
	  public void testFindUserMenus() {
		  List<SysUserMenuVo> list=
		  sysMenuDao.findUserMenus();
		  for(SysUserMenuVo um:list) {
		  System.out.println(um);
		  }
	  }
	  @Test
	  public void testFirstLevelCache() {
		  SqlSession session1=sessionFactory.openSession();
		  SysMenuDao smd=session1.getMapper(SysMenuDao.class);
		  Map<String,Object> map1=smd.findById(30);
		  Map<String,Object> map2=smd.findById(30);
		  System.out.println(map1==map2);
		  session1.close();//session1的一级缓存失效
	  }
	  @Test
	  public void testSecondLevelCache01() {
		  SqlSession session1=sessionFactory.openSession();
		  SysMenuDao smd=session1.getMapper(SysMenuDao.class);
		  Map<String,Object> map1=smd.findById(30);//命中率0 (二级缓存)
		  Map<String,Object> map2=smd.findById(30);//命中率0
		  System.out.println(map1==map2);
		  session1.close();//session1的一级缓存失效,此数据存储到二级缓存
		  SqlSession session2=sessionFactory.openSession();
		  SysMenuDao dao=session2.getMapper(SysMenuDao.class);
		  Map<String,Object> map3=dao.findById(30);//命中率0.333333
		  Map<String,Object> map4=dao.findById(30);//命中率0.5
		  System.out.println(map3==map4);
		  session2.close();
	  }
	  
	  
	  @Test
	  public void testSecondLevelCache02() {
		  Map<String,Object> map1= 
		  sysMenuDao.findById(130);
		  Map<String,Object> map2=
		  sysMenuDao.findById(130);
		  System.out.println(map1==map2);
	  }
	  
	  @Test
	  public void testFindObjects() {
		  long t1=System.nanoTime();
		  List<Map<String,Object>> list=
		  sysMenuDao.findObjects();
		  System.out.println("执行时长:"+(System.nanoTime()-t1));
		  for(Map<String,Object> map:list) {
			  System.out.println(map);
		  }
		  //更加优雅输出
		  //list.forEach((map)->System.out.println(map));
	  }//执行计划(SQL调优)
}










