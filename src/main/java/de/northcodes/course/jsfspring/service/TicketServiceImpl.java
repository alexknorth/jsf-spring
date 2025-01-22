package de.northcodes.course.jsfspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.Ticket;
import de.northcodes.course.jsfspring.persistence.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketServiceImpl implements TicketService {

	private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Autowired
	private TicketRepository ticketRepository;

	public long getNextTicketId() {
		Long maxId = ticketRepository.findMaxTicketId(); // Repository-Methode aufrufen, um die höchste ID zu ermitteln
		long nextId = (maxId != null ? maxId + 1 : 1); // Wenn keine Tickets vorhanden sind, starte bei ID 1
		log.info("Calculated next Ticket ID: {}", nextId);
		return nextId;
	}

	@Override
	public List<Ticket> getAllTickets() {
		log.info("getAllTickets called");
		return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Ticket getTicket(long id) {
		log.info("getTicket called with id: {}", id);
		// Suche ein Ticket anhand der ID
		return ticketRepository.findById(id).orElse(null);
	}

	@Override
	public List<Ticket> getTicketsByStatus(String status) {
		log.info("getTicketsByStatus called with status: {}", status);
		return ticketRepository.findByStatus(status);
	}

	@Override
	public void createTicket(Ticket ticket) {
		// Wenn das Ticket keine Ticket-ID hat, berechne die nächste ID
		if (ticket.getTicketId() == 0) {
			ticket.setTicketId(getNextTicketId()); // Weise die nächste ID zu
		}
		ticketRepository.save(ticket);
		log.info("Ticket with ID {} created", ticket.getTicketId());
	}

	@Override
	public void updateTicket(Ticket ticket) {
		ticketRepository.save(ticket);
		log.info("Ticket with ID {} updated", ticket.getTicketId());
	}


}


