package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor <Ticket> {
    List<Ticket> findByStatus(String status);
}