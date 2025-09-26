package com.bit2025.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bit2025.jblog.config.app.DBConfig;
import com.bit2025.jblog.config.app.MyBatisConfig;

/**
 * applicationContext.xml
 * Config Hub
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.bit2025.jblog.repository", "com.bit2025.jblog.service"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}
