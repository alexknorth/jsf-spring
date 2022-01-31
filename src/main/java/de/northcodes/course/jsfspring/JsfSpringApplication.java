package de.northcodes.course.jsfspring;

import java.util.Arrays;
import java.util.Date;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.persistence.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

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
    public CommandLineRunner demo(RecipeRepository repository) {
      return (args) -> {
        // save a few recipes

        repository.save( new Recipe("Spaghetti Bolognese", 5, "spagehetti & soße", "blabla", "20min", new Date()));
        repository.save( new Recipe("Lasagne", 4, "bla", "bla", "1h", new Date()));
        repository.save( new Recipe("Ofenkartoffeln", 4, "bla", "bla", "30min", new Date()));

        // fetch all recipes
        log.info("Recipes found with findAll():");
        log.info("-------------------------------");
        for (Recipe recipe : repository.findAll()) {
          log.info(recipe.toString());
        }
        log.info("");

        // fetch an individual recipes by ID
        Recipe recipe = repository.findById(1L).get();
        log.info("Recipes found with findById(1L):");
        log.info("--------------------------------");
        log.info(recipe.toString());
        log.info("");

      };
    }
}