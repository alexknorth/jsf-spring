
package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Meilenstein;

public interface MeilensteinRepository extends CrudRepository<Meilenstein, Long> {
}