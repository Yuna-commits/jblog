package com.bit2025.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 * = ( @Configuration + @EnableAutoConfiguration + @ComponentScan )
 */
@SpringBootApplication
public class JBlogApplication {
	// Entry Point
	public static void main(String[] args) { 
		/**
		 * BootStrapping Method
		 * 
		 * SpringApplication.run(...) 호출
		 * > 1. Spring ApplicationContext 생성
		 * > 2. Auto-Configuration
		 * > 3. 내장 톰캣 서버 시작
		 * > 4. Bean 초기화, 의존성 주입 수행
		 */
		SpringApplication.run(JBlogApplication.class, args);
	}

}
