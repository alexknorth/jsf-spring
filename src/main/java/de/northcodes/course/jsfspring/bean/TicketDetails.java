package de.northcodes.course.jsfspring.bean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;

import de.northcodes.course.jsfspring.model.Ticket;
import de.northcodes.course.jsfspring.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.service.TicketService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.ManagedBean;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
@ViewScoped
@ManagedBean
public class TicketDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserManager userManager;

    private Ticket ticket;

    // Listen für Ticket-Dropdown
    private List<String> priorityOptions;
    private List<String> statusOptions;
    private List<String> affectedSystemOptions;

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
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isNewTicket = context != null &&
                "true".equals(context.getExternalContext().getRequestParameterMap().get("new"));

        if (isNewTicket || ticket == null || ticket.getId() == null) {
            ticket = new Ticket();
            ticket.initializeCreationDateTime();

            // Setze den Creator basierend auf dem angemeldeten Benutzer
            if (userManager != null && userManager.getCurrentUser() != null) {
                ticket.setCreator(userManager.getCurrentUser().getUsername()); // `creator` setzen
            } else {
                ticket.setCreator("Unknown");
            }

        } else {
            ticket = ticketService.getTicket(ticket.getId());
        }

        // Initialisiere die Dropdown-Werte
        priorityOptions = Arrays.asList("Low", "Medium", "High", "Critical");
        statusOptions = Arrays.asList("Open", "Work in progress", "Suspended", "Resolved");
        affectedSystemOptions = Arrays.asList("System A", "System B", "System C", "System D");
    }

    // Methode zum Laden der Ticketdetails bestehender Tickets
    public void loadTicketDetails(Long ticketId) {
        ticket = ticketService.getTicket(ticketId); // Bestehendes Ticket laden

        // Navigiere zur Ticket-Detailseite
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "ticket-details.xhtml");
    }

    // Speichern eines Tickets (neu oder bestehend)
    public String saveTicket() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean hasErrors = false;

        // Validierung der Pflichtfelder
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
        if (ValidationUtils.isFieldEmpty(ticket.getPrio())) {
            context.addMessage("prio",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Priority is required.", null));
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

        // Validierung bei Status "Resolved"
        if ("Resolved".equals(ticket.getStatus())) {

            if (ValidationUtils.isFieldEmpty(ticket.getSolutionTxt())) {
                context.addMessage("solutionTxt",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solution must not be empty for 'Resolved' status.", null));
                hasErrors = true;
            }else if (ticket.getSolvingDate() == null || ticket.getSolvingDate().isEmpty()) {
                // Automatisches Setzen von Datum und Uhrzeit
                ticket.initializeSolvingDateTime();
            }
        }else {
            // Status ist nicht mehr "Resolved", daher Datum und Uhrzeit löschen
            ticket.setSolvingDate(null);
            ticket.setSolvingTime(null);
        }

        // Wenn Fehler vorhanden sind, abbrechen
        if (hasErrors) {
            return null; // Bleibt auf der gleichen Seite
        }

        // Speichern des Tickets
        if (ticket.getId() == null || ticket.getId() == 0) { // Null-Check hinzufügen
            ticketService.createTicket(ticket);
        } else {
            ticketService.updateTicket(ticket);
        }

        // **Neu Laden der Ticket-Liste**
        FacesContext.getCurrentInstance().getExternalContext()
                .getApplicationMap()
                .put("ticketManager", null); // Cache leeren

        // Zur Übersicht zurückleiten
        return "tickets.xhtml?faces-redirect=true";
    }

}