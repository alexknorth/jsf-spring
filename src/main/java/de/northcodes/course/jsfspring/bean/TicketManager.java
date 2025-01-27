package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.RequestScoped;
import javax.faces.event.PreRenderViewEvent;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.service.TicketService;

import java.io.IOException;
import java.util.List;

@RequestScoped
@Component
@ManagedBean
public class TicketManager {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private UserManager userManager; // Zugriff auf den angemeldeten Benutzer

	// Filterwerte
	private Long filterTicketId;
	private String filterTicketName;
	private String filterStatus;
	private String filterPriority;
	private String filterCreationDate;

	// Gefilterte Liste
	private List<Ticket> filteredTickets;


	public void checkAccess() {
		if (!userManager.isSignedIn()) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Event-Listener, der vor dem Rendering der Seite aufgerufen wird
	public void onPreRenderView(PreRenderViewEvent event) {
		checkAccess();
	}

	// Methode zum Abrufen der Tickets
	public List<Ticket> getTickets() {
		if (filteredTickets == null) {
			return ticketService.getAllTickets(); // Originale Liste zurückgeben
		}
		return filteredTickets;
	}

	// Methode zum Anwenden der Filter
	public void applyFilters() {
		filteredTickets = ticketService.getFilteredTickets(filterTicketId, filterTicketName, filterStatus, filterPriority, filterCreationDate);
//		List<Ticket> allTickets = ticketService.getAllTickets(); // Hole die gesamte Ticketliste
//		filteredTickets = allTickets.stream()
//				.filter(ticket -> filterTicketId == null || filterTicketId.isEmpty() || ticket.getId().toString().contains(filterTicketId))
//				.filter(ticket -> filterTicketName == null || filterTicketName.isEmpty() || ticket.getTicketName().contains(filterTicketName))
//				.filter(ticket -> filterStatus == null || filterStatus.isEmpty() || ticket.getStatus().equals(filterStatus))
//				.filter(ticket -> filterPriority == null || filterPriority.isEmpty() || ticket.getPrio().equals(filterPriority))
//				.filter(ticket -> filterCreationDate == null || filterCreationDate.isEmpty() || ticket.getCreationDate().equals(filterCreationDate))
//				.collect(Collectors.toList());
	}

	// Getter und Setter für die Filterwerte
	public Long getFilterTicketId() {
		return filterTicketId;
	}

	public void setFilterTicketId(Long filterTicketId) {
		this.filterTicketId = filterTicketId;
	}

	public String getFilterTicketName() {
		return filterTicketName;
	}

	public void setFilterTicketName(String filterTicketName) {
		this.filterTicketName = filterTicketName;
	}

	public String getFilterStatus() {
		return filterStatus;
	}

	public void setFilterStatus(String filterStatus) {
		this.filterStatus = filterStatus;
	}

	public String getFilterPriority() {
		return filterPriority;
	}

	public void setFilterPriority(String filterPriority) {
		this.filterPriority = filterPriority;
	}

	public String getFilterCreationDate() {
		return filterCreationDate;
	}

	public void setFilterCreationDate(String filterCreationDate) {
		this.filterCreationDate = filterCreationDate;
	}
}
