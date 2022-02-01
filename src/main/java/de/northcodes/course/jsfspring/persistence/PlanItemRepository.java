package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.PlanItem;

public interface PlanItemRepository extends CrudRepository<PlanItem, Long> {

}