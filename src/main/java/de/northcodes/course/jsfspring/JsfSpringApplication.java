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
    
    
    //Only need for development initialization purposes
    @Bean
    public CommandLineRunner demo(TicketRepository repository) {
      return (args) -> {
        // save a few Tickets

          repository.save(new Ticket("TICKET001", "2025-01-01", "10:00", "Network Issue",
                  "The network in building A is down.", 1, "Open", "Network", "IT Support",
                  null, null, null));
          repository.save(new Ticket("TICKET002", "2025-01-02", "11:30", "Email Problem",
                  "Emails are not being sent from the marketing team.", 2, "Open", "Email", "IT Support",
                  null, null, null));
          repository.save(new Ticket("TICKET003", "2025-01-03", "15:45", "Printer Error",
                  "The main office printer is showing a paper jam error.", 3, "Open", "Hardware", "Facilities",
                  null, null, null));
        // fetch all products
        log.info("Tickets found with findAll():");
        log.info("-------------------------------");
        for (Ticket ticket : repository.findAll()) {
          log.info(ticket.toString());
        }
        log.info("");

        // fetch an individual Ticket by ID
          Ticket ticket = repository.findById(1L).orElse(null);
          log.info("Ticket found with findById(1L):");
          log.info("--------------------------------");
          if (ticket != null) {
              log.info(ticket.toString());
          } else {
              log.info("No ticket found with ID 1");
          }
          log.info("");
      };
    }
}