package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Date;

@ViewScoped
@Component
@ManagedBean
public class UserDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private UserService userService;

	private User user;

	public User getUser() {
		return user;
	}

	public void onload() {
		user = userManager.isSignedIn() ? userManager.getCurrentUser() : new User();
	}

	public String submit() {
		return userManager.save(user);
	}

	public void validateEmail(FacesContext context, UIComponent component, Object value) {
		String emailAddress = (String) value;
		if (
				emailAddress == null || 
				emailAddress.isEmpty() || 
				!emailAddress.contains("@") || 
				!emailAddress.contains(".") || 
				(!isUserOwnEmailAddress(emailAddress) && userService.isEmailAlreadyExisting(emailAddress))
			) {
			throw new ValidatorException(new FacesMessage("Please enter a valid e-mail address."));
		}
	}

	private boolean isUserOwnEmailAddress(String emailAddress) {
		return user.getEmailAddress() != null && user.getEmailAddress().equals(emailAddress);
	}
	
	public void validateBirthDate(FacesContext context, UIComponent component, Object value) {
		Date birthDate = (Date) value;
		if (birthDate == null || !birthDate.before(new Date())) {
			throw new ValidatorException(new FacesMessage("Please enter a valid birth date."));
		}
	}
}
