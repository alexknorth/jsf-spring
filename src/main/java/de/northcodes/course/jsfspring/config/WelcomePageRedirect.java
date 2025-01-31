package de.northcodes.course.jsfspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import de.northcodes.course.jsfspring.bean.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WelcomePageRedirect.class);

	@Autowired
	private UserManager userManager;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		if (userManager.isSignedIn()) {
			logger.info("User is signed in, redirecting to workout.xhtml");
			registry.addViewController("/").setViewName("forward:/workout.xhtml?faces-redirect=true");
		} else {
			logger.info("User is not signed in, redirecting to sign-in.xhtml");
			registry.addViewController("/").setViewName("forward:/sign-in.xhtml?faces-redirect=true");
		}
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
}