package de.northcodes.course.jsfspring.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.service.TicketService;

import java.io.Serializable;
import javax.annotation.ManagedBean;

@Component
@ViewScoped
@ManagedBean
public class TicketDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TicketService ticketService;

    private long ticketId;

    private Ticket ticket;

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    // Onload-Methode zum Laden der Ticketdetails
    @PostConstruct
    public void onload() {
        // Stelle sicher, dass FacesContext zu diesem Zeitpunkt verfügbar ist
        if (FacesContext.getCurrentInstance() != null) {
            ticket = ticketService.getTicket(ticketId);
        }
    }

    // Methode zum Laden der Ticketdetails
    public void loadTicketDetails(Long ticketId) {
        // Verwende ticketService, um die Ticketdetails basierend auf der ID zu laden
        ticket = ticketService.getTicket(ticketId);

        // Navigiere zur Ticket-Detailseite
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "ticket-details?faces-redirect=true");
    }
}