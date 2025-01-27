package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Ticket;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicket(Long id);

    List<Ticket> getTicketsByStatus(String status);

    void updateTicket(Ticket ticket);

    void createTicket(Ticket ticket);

    List<Ticket> getFilteredTickets(Long filterTicketId, String filterTicketName, String filterStatus,
                                    String filterPriority, String filterCreationDate);
}
