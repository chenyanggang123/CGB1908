package com.cy;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableAsync //表示要开启异步配置
@EnableCaching //开启spring中的缓存配置
@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private AbstractAdvisorAutoProxyCreator proxyCreator;
	public Application() {}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("run.proxyCreator="+proxyCreator.getClass());
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
