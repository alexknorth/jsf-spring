package de.northcodes.course.jsfspring.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        if (ticketId != 0) { // Ticket mit ID existiert
            ticket = ticketService.getTicket(ticketId);
        } else {
            ticket = new Ticket(); // Neues Ticket für die Erstellung

            // Setze das aktuelle Datum und die aktuelle Uhrzeit
            ticket.setCreationDate(java.time.LocalDate.now().toString());
            ticket.setCreationTime(java.time.LocalTime.now().toString());
        }
    }

    // Speichern eines Tickets (neu oder bestehend)
    public String saveTicket() {
        if (ticketId == 0) { // Neues Ticket
            if (ticket.getCreationDate() == null || ticket.getCreationDate().isEmpty()) {
                ticket.setCreationDate(java.time.LocalDate.now().toString());
            }
            if (ticket.getCreationTime() == null || ticket.getCreationTime().isEmpty()) {
                ticket.setCreationTime(java.time.LocalTime.now().toString());
            }
            ticketService.createTicket(ticket);
        } else { // Bestehendes Ticket
            ticketService.updateTicket(ticket);


        }
        return "tickets.xhtml?faces-redirect=true"; // Nach dem Speichern zur Ticket-Liste zurück
    }

    // Methode zum Laden der Ticketdetails
    public void loadTicketDetails(Long ticketId) {
        // Verwende ticketService, um die Ticketdetails basierend auf der ID zu laden
        ticket = ticketService.getTicket(ticketId);

        // Navigiere zur Ticket-Detailseite
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "ticket-details.xhtml");
    }
}