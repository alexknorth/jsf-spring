package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Trainingsplan;
import org.springframework.data.repository.CrudRepository;

public interface TrainingsplanRepository extends CrudRepository<Trainingsplan, Long> {
}