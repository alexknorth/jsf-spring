package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Uebung;

public interface UebungRepository extends CrudRepository<Uebung, Long> {
}