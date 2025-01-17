package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.UserService;

import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Component
@ManagedBean
public class UserManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    private User currentUser;

    public boolean isSignedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String signIn(String username, String password) {
        User user = userService.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
        	 FacesContext.getCurrentInstance().addMessage(null,
                     new FacesMessage("Please enter a valid username and password."));
            return "sign-in";
        }

        currentUser = user;
        return "tickets?faces-redirect=true";
    }

    public String signOut() {
        // End the session, removing any session state, including the current user and content of the shopping cart
        currentUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Redirect is necessary to let the browser make a new GET request
        return "index?faces-redirect=true";
    }

    public String save(User user) {
        try {
            userService.saveUser(user); // Speichert den Benutzer in der Datenbank
            currentUser = user; // Setzt den aktuellen Benutzer in die Sitzung

            // Weiterleitung zur Tickets-Seite
            return "tickets?faces-redirect=true";
        } catch (Exception e) {
            // Fehlerbehandlung
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error occurred during registration.", null));
            return null; // Bleibt auf der aktuellen Seite
        }
    }
    public void checkAccess() {
        if (!isSignedIn()) {
            try {
                // Falls der Benutzer nicht eingeloggt ist, leite ihn zur Anmeldeseite weiter
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
