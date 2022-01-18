package de.northcodes.course.jsfspring.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByOrderer(User orderer);
}