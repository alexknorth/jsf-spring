package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Set;
import de.northcodes.course.jsfspring.model.WorkoutExercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SetRepository extends CrudRepository<Set, Long> {
    List<Set> findByWorkoutExerciseId(Long workoutExerciseId);
}