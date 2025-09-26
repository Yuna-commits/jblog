package com.bit2025.jblog.initializer;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.bit2025.jblog.config.AppConfig;
import com.bit2025.jblog.config.WebConfig;

import jakarta.servlet.Filter;
import jakarta.servlet.ServletRegistration.Dynamic;

/**
 * web.xml
 * DispatcherServlet 등록
 */
public class JBlogWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	// Root Context
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}

	// Web Context : DispatcherServlet 등록
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
	
	// servlet-mapping : url-pattern
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// spring security : DelegatingFilterProxy 등록
	@Override
	protected Filter[] getServletFilters() {
		return super.getServletFilters();
	}

	// File Upload :  multipart-config
	@Override
	protected void customizeRegistration(Dynamic registration) {
		super.customizeRegistration(registration);
	}

}
