package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Ticket;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TicketSpecification {

    public static Specification<Ticket> filterBy(
            Long filterTicketId, String filterTicketName, String filterStatus,
            String filterPriority, String filterCreationDate) {

        return (Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            // Liste für Bedingungen
            List<Predicate> predicates = new ArrayList<>();

            // Filterbedingungen hinzufügen
            if (filterTicketId != null) {
                predicates.add(cb.equal(root.get("id"), filterTicketId));
            }
            if (filterTicketName != null && !filterTicketName.isEmpty()) {
                predicates.add(cb.like(root.get("ticketName"), "%" + filterTicketName + "%"));
            }
            if (filterStatus != null && !filterStatus.isEmpty()) {
                predicates.add(cb.equal(root.get("status"), filterStatus));
            }
            if (filterPriority != null && !filterPriority.isEmpty()) {
                predicates.add(cb.equal(root.get("prio"), filterPriority));
            }
            if (filterCreationDate != null && !filterCreationDate.isEmpty()) {
                predicates.add(cb.equal(root.get("creationDate"), filterCreationDate));
            }

            // Kombiniere alle Bedingungen mit "AND"
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
