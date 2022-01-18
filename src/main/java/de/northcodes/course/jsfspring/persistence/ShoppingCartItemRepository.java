
package de.northcodes.course.jsfspring.persistence;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.ShoppingCartItem;

public interface ShoppingCartItemRepository extends CrudRepository<ShoppingCartItem, Long> {
}