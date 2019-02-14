package org.asu.cse545.group4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


@Configuration
@ComponentScan(basePackages = "org.asu.cse545.group4")
public class ApplicationConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
	    //return new Class[] { AppConfig.class, WebSecurityConfig.class };
		return new Class[] { PersistenceConfiguration.class};
	  }

	  // Load spring web configuration
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
	    return new Class[] { WebConfiguration.class };
	  }
	  

	  @Override
	  protected String[] getServletMappings() {
	    return new String[] { "/rest/*" };
	  }

}
