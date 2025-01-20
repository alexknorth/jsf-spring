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

	//Methode zum Abrufen der Tickets
	public List<Ticket> getTickets() {
		return ticketService.getAllTickets();
	}
}
