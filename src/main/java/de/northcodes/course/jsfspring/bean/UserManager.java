package de.northcodes.course.jsfspring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.UserService;

@Component
@Scope("session") // Spring-spezifische Annotation für Session-Scoped Beans
public class UserManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    private User currentUser;

    private String username;
    private String password;

    public boolean isSignedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String signIn() {
        User user = userService.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login", "Invalid username or password."));
            return null;
        }

        currentUser = user;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("tickets.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void signOut() {
        currentUser = null;
        username = null;
        password = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String save(User user) {
        userService.saveUser(user);
        currentUser = user;
        return "index";
    }
}
