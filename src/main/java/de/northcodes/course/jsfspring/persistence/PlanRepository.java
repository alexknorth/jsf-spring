package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan, Long> {
}