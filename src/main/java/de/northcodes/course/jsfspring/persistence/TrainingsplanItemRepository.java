package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.TrainingsplanItem;

public interface TrainingsplanItemRepository extends CrudRepository<TrainingsplanItem, Long> {

}