package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.bean.TicketSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

	@Override
	public List<Ticket> getAllTickets() {
		log.info("getAllTickets called");
		return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Ticket getTicket(Long id) {
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
		ticketRepository.save(ticket);
		log.info("Ticket with ID {} created", ticket.getId());
	}

	@Override
	public void updateTicket(Ticket ticket) {
		ticketRepository.save(ticket);
		log.info("Ticket with ID {} updated", ticket.getId());
	}

	@Override
	public List<Ticket> getFilteredTickets(Long filterTicketId, String filterTicketName, String filterStatus,
										   String filterPriority, String filterCreationDate) {
		Specification<Ticket> spec = TicketSpecification.filterBy(
				filterTicketId, filterTicketName, filterStatus, filterPriority, filterCreationDate);

		return ticketRepository.findAll(spec);
	}


}


