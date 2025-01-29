package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Exercise;
import de.northcodes.course.jsfspring.model.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {

	List<Workout> findByNameContainingIgnoreCase(String name);
}