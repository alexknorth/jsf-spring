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

import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.persistence.ProductRepository;

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
    
    
    //Only need for development initialization purposes
    @Bean
    public CommandLineRunner demo(ProductRepository repository) {
      return (args) -> {
        // save a few products

        repository.save( new Product("Microphone", "Essential for every vocalist - this microphone makes your voice sound great. Suitable for any kind of music and any voice.", new BigDecimal("95.00"), "microphone"));
        repository.save( new Product("Guitar", "This guitar sounds great and looks cool. Rock, blues or jazz, this guitar does it all.", new BigDecimal("995.00"), "guitar"));
        repository.save( new Product("Saxophone", "Steal the show with this cool saxophone. Suitable for beginners as well as for advanced players.", new BigDecimal("1195.00"), "saxophone"));
        repository.save( new Product("Bass Guitar", "Every band needs a solid bass guitar. This one will never let you down.", new BigDecimal("895.00"), "bassguitar"));
        repository.save( new Product("Drum Kit", "This complete drum kit provides everything a drummer needs. Including an extra pair of sticks.", new BigDecimal("1249.00"), "drumkit"));

        // fetch all products
        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product product : repository.findAll()) {
          log.info(product.toString());
        }
        log.info("");

        // fetch an individual product by ID
        Product product = repository.findById(1L).get();
        log.info("Product found with findById(1L):");
        log.info("--------------------------------");
        log.info(product.toString());
        log.info("");

      };
    }
}