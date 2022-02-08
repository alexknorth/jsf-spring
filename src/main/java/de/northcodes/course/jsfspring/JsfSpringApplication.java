package de.northcodes.course.jsfspring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import de.northcodes.course.jsfspring.model.Muskelgruppe;
import de.northcodes.course.jsfspring.model.Uebung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import de.northcodes.course.jsfspring.persistence.UebungRepository;

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
        ServletRegistrationBean<FacesServlet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Collections.singletonList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }
    
    
    //Only need for development initialization purposes
    @Bean
    public CommandLineRunner demo(UebungRepository uebungRepository) {
      return (args) -> {
        // save a few products

        uebungRepository.save(new Uebung(
                "Squats",
                "Zu deutsch: Kniebeugen. Gute overall Beinübung.",
                Arrays.asList(Muskelgruppe.GESAESS, Muskelgruppe.UNTERERRUECKEN, Muskelgruppe.OBERSCHENKEL)));

          uebungRepository.save(new Uebung(
                  "Hip Thrusts",
                  "Übung, die die Gesäßmuskulatur beansprucht.",
                  Arrays.asList(Muskelgruppe.GESAESS, Muskelgruppe.OBERSCHENKEL)));

        // fetch all products
//        log.info("Products found with findAll():");
//        log.info("-------------------------------");
//        for (Uebung excercise : repository.findAll()) {
//          log.info(excercise.toString());
//        }
//        log.info("");
//
//        // fetch an individual product by ID
//        Uebung excercise = repository.findById(1L).get();
//        log.info("Product found with findById(1L):");
//        log.info("--------------------------------");
//        log.info(excercise.toString());
//        log.info("");

      };
    }
}