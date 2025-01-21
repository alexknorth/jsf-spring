package de.northcodes.course.jsfspring.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.service.TicketService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
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

    // Listen für Ticket-Dropdown
    private List<String> priorityOptions;
    private List<String> statusOptions;
    private List<String> affectedSystemOptions;

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public List<String> getPriorityOptions() {
        return priorityOptions;
    }

    public List<String> getStatusOptions() {
        return statusOptions;
    }

    public List<String> getAffectedSystemOptions() {
        return affectedSystemOptions;
    }

    // Onload-Methode zum Laden der Ticketdetails
    @PostConstruct
    public void onload() {
        if (ticketId != 0) { // Ticket mit ID existiert
            ticket = ticketService.getTicket(ticketId);
        } else {
            ticket = new Ticket(); // Neues Ticket für die Erstellung
        }
        // Initialisiere Ticket-Dropdown
        priorityOptions = Arrays.asList("Low", "Medium", "High", "Critical");
        statusOptions = Arrays.asList("Open", "Work in progress", "Suspended", "Resolved");
        affectedSystemOptions = Arrays.asList("System A", "System B", "System C", "System D");
    }


    // Speichern eines Tickets (neu oder bestehend)
    public String saveTicket() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean hasErrors = false;

        // Validierung der Pflichtfelder
        if (ValidationUtils.isFieldEmpty(ticket.getTicketId())) {
            context.addMessage("ticketId",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket ID is required.", null));
            hasErrors = true;
        }
        if (ValidationUtils.isFieldEmpty(ticket.getTicketName())) {
            context.addMessage("ticketName",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket Name is required.", null));
            hasErrors = true;
        }
        if (ValidationUtils.isFieldEmpty(ticket.getDescription())) {
            context.addMessage("description",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Description is required.", null));
            hasErrors = true;
        }
        if (ValidationUtils.isFieldEmpty(ticket.getStatus())) {
            context.addMessage("status",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Status is required.", null));
            hasErrors = true;
        }
        if (ValidationUtils.isFieldEmpty(ticket.getAffectedSystem())) {
            context.addMessage("affectedSystem",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Affected System is required.", null));
            hasErrors = true;
        }
        if (ValidationUtils.isFieldEmpty(ticket.getResolverGroup())) {
            context.addMessage("resolverGroup",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Resolver Group is required.", null));
            hasErrors = true;
        }

        // Wenn Fehler vorhanden sind, abbrechen
        if (hasErrors) {
            return null; // Bleibt auf der gleichen Seite
        }

        // Speichern des Tickets
        if (ticketId == 0) {
            ticketService.createTicket(ticket);
        } else {
            ticketService.updateTicket(ticket);
        }
        return "tickets.xhtml?faces-redirect=true";
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