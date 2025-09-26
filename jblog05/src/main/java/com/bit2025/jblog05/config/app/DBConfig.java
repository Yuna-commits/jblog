package com.bit2025.jblog05.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

@Configuration
@PropertySource("classpath:config/app/jdbc.properties")
public class DBConfig {
	
	@Autowired
	private Environment env;
	
	// DataSource(Connection Pool) from jdbc.properties
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		
		return dataSource;
	}
	
	// Transaction Manager
	@Bean
	public TransactionManager transactionManager(DataSource dataSource) {
		// 스프링이 자동으로 생성자 주입(Dependency Injection)
		return new DataSourceTransactionManager(dataSource);
	}
}
