package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Template;
import de.northcodes.course.jsfspring.model.Workout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<Workout, Long> {
    Workout findTopByTemplateOrderByIdDesc(Template template);

    List<Workout> findByTemplateId(Long templateId);

    List<Workout> findAllByOrderByIdDesc();
}