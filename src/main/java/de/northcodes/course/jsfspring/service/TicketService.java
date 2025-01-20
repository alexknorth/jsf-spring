package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Ticket;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicket(long id);

    List<Ticket> getTicketsByStatus(String status);

}
