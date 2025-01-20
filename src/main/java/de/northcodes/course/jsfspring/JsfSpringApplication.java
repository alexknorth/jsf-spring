package de.northcodes.course.jsfspring;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import de.northcodes.course.jsfspring.model.Ticket;
import de.northcodes.course.jsfspring.persistence.TicketRepository;

@SpringBootApplication
public class JsfSpringApplication extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(JsfSpringApplication.class);
	
    public static void main(String[] args) {
        SpringApplication.run(JsfSpringApplication.class, args);
    }

    @Bean
    ServletRegistrationBean<FacesServlet> jsfServletRegistration (ServletContext servletContext) {
    	log.info("jsfServletRegistration started...");
    	
        //spring boot only works if this is set
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        //FacesServlet registration
        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<FacesServlet>();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }
}