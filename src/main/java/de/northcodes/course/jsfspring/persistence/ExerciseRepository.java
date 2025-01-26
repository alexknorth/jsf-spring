package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Exercise;
import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

	List<Exercise> findByNameContainingIgnoreCase(String name);
}