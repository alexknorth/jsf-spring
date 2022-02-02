package de.northcodes.course.jsfspring;

import java.util.Arrays;
import java.util.Date;

import javax.faces.webapp.FacesServlet;
import javax.persistence.Column;
import javax.servlet.ServletContext;

import de.northcodes.course.jsfspring.bean.MoneyTransfer;
import de.northcodes.course.jsfspring.model.TransferDetails;
import de.northcodes.course.jsfspring.model.TransferState;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.persistence.TransferRepository;
import de.northcodes.course.jsfspring.persistence.UserRepository;
import de.northcodes.course.jsfspring.service.TransferServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JsfSpringApplication extends SpringBootServletInitializer {

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransferServiceImpl transferService;

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
    public CommandLineRunner demo() {
      return (args) -> {
          User user = this.userRepository.save(new User(123, "1234", "teoman", "kinaci", "t.k.@gmail.de", "12234523", new Date(), 1230.12));
          User user2 = this.userRepository.save(new User(456, "1234", "Zweiter", "User", "Zweiteruser@gmail.de", "12234523", new Date(), 2000.00));
          TransferDetails firstTransfer = this.transferRepository.save(new TransferDetails(new Date(), user, user2, TransferState.READY, 450.00));
          transferService.transferNow(firstTransfer);



      };
    }

}