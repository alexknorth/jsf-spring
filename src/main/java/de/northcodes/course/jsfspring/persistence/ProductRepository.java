package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}