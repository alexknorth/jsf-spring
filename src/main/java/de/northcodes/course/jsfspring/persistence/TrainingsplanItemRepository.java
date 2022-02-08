package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.TrainingsplanItem;

import java.util.List;

public interface TrainingsplanItemRepository extends CrudRepository<TrainingsplanItem, Long> {

}