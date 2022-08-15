package com.learn.pdw.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ProgDispatcherServletWebApplicationInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		XmlWebApplicationContext rootApplicationCotext = null;
		XmlWebApplicationContext servletApplicationContext = null;
		DispatcherServlet dispatcherServlet = null;
		ContextLoaderListener contextLoaderListener = null;
		
		rootApplicationCotext = new XmlWebApplicationContext();
		rootApplicationCotext.setConfigLocation("/WEB-INF/application-context.xml");
		contextLoaderListener = new ContextLoaderListener(rootApplicationCotext);
		servletContext.addListener(contextLoaderListener);
		
		servletApplicationContext = new XmlWebApplicationContext();
		servletApplicationContext.setConfigLocation("/WEB-INF/dispatcher-servlet.xml");
		dispatcherServlet = new DispatcherServlet(servletApplicationContext);
		
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", dispatcherServlet);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("*.htm");
	}
}
