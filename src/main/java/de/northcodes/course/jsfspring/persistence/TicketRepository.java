package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByStatus(String status);
}