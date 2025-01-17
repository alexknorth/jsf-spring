package de.northcodes.course.jsfspring.persistence;

import java.util.Date;
import java.util.List;

import de.northcodes.course.jsfspring.model.OrderState;
import org.springframework.data.repository.CrudRepository;

import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByOrderer(User orderer);
}