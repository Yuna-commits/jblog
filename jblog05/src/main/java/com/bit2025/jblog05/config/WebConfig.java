package com.bit2025.jblog05.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bit2025.jblog05.config.web.FileuploadConfig;
import com.bit2025.jblog05.config.web.MvcConfig;

/**
 * dispatcher-servlet.xml
 * Config Hub
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.bit2025.jblog.controller"})
@Import({FileuploadConfig.class, MvcConfig.class})
public class WebConfig implements WebMvcConfigurer {

}
