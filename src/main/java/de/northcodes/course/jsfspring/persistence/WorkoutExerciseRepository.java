package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Set;
import de.northcodes.course.jsfspring.model.Template;
import de.northcodes.course.jsfspring.model.Workout;
import de.northcodes.course.jsfspring.model.WorkoutExercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkoutExerciseRepository extends CrudRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutId(Long workoutId);
}