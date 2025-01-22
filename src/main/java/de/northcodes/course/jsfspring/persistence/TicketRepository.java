package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findByStatus(String status);

    // Methode zur Bestimmung der maximalen Ticket-ID
    @Query("SELECT MAX(t.ticketId) FROM Ticket t")
    Long findMaxTicketId(); // Abfrage für die höchste Ticket-ID
}