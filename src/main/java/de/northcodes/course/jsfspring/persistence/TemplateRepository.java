package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Exercise;
import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.Template;
import de.northcodes.course.jsfspring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TemplateRepository extends CrudRepository<Template, Long> {

    List<Template> findByNameContainingIgnoreCase(String name);
}