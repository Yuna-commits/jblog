package com.bit2025.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication
 * - @SpringBootConfiguration + @EnableAutoConfiguration + @ComponentScan
 */
@SpringBootApplication
public class JBlogApplication {
	// Entry Point
	public static void main(String[] args) { 
		/**
		 * BootStrapping Method
		 * 
		 * SpringApplication.run(...) 호출
		 * > 1. SpringApplication 객체 생성, 환경 초기화
		 * > 2. ApplicationContext 생성
		 * > 3. @EnableAutoConfiguration에 따라 자동 설정 클래스 로드
		 * > 4. @ComponentScan으로 사용자 정의 Bean 스캔, 등록
		 * > 5. 내장 톰캣 서버 생성, 시작
		 * > 6. Bean 초기화, 의존성 주입 수행
		 */
		SpringApplication.run(JBlogApplication.class, args);
	}

}
