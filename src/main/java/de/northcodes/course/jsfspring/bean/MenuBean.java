package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.JsfSpringApplication;
import de.northcodes.course.jsfspring.model.MenuItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class MenuBean implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(MenuBean.class);
    private final List<MenuItem> menuItems = new ArrayList<>();
    private String activePage;

    public MenuBean() {
        // Menüeinträge initialisieren
        menuItems.add(new MenuItem("Profile", "profile.xhtml"));
        menuItems.add(new MenuItem("Recent", "recent.xhtml"));
        menuItems.add(new MenuItem("Workout", "workout.xhtml"));
        menuItems.add(new MenuItem("Exercises", "exercises.xhtml"));
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public String getActivePage() {
        activePage = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        log.info(activePage);
        return activePage;
    }

    public void setActivePage(String activePage) {
        this.activePage = activePage;
    }

    public boolean isActive(String page) {
        String test = this.getActivePage();
        return page.equals("index");
    }
}
