package com.cy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringTests {

	@Test
	public void testStr01() {
		String s1=null;
		String s2="";
		System.out.println(s1==s2);
		String s3=null;
		System.out.println(s1==s3);
		if(s3==null||"".equals(s3)) {
			
		}
		
	}
}
