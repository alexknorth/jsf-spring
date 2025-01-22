package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Ticket;

public interface TicketService {

    List<Ticket> getAllTickets();

    Ticket getTicket(long id);

    List<Ticket> getTicketsByStatus(String status);

    void updateTicket(Ticket ticket);

    void createTicket(Ticket ticket);

    long getNextTicketId(); // Neue Methode für inkrementelle ID
}
