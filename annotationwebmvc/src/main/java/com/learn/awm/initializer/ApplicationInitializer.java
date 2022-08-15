package com.learn.awm.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.learn.awm.config.RootConfig;
import com.learn.awm.config.WebConfig;

public class ApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext rootApplicationContext = null;
		AnnotationConfigWebApplicationContext servletApplicationContext = null;
		DispatcherServlet dispatcherServlet = null;
		ContextLoaderListener contextLoaderListener = null;
		
		rootApplicationContext = new AnnotationConfigWebApplicationContext();
		rootApplicationContext.register(RootConfig.class);
		contextLoaderListener = new ContextLoaderListener(rootApplicationContext);
		servletContext.addListener(contextLoaderListener);
		
		servletApplicationContext = new AnnotationConfigWebApplicationContext();
		servletApplicationContext.register(WebConfig.class);
		dispatcherServlet = new DispatcherServlet(servletApplicationContext);
		
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", dispatcherServlet);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("*.htm");
	}

	
}
