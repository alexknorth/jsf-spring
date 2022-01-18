package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.ShoppingCartItem;
import de.northcodes.course.jsfspring.model.User;

public interface OrderService {

	public void orderNow(User orderer, List<ShoppingCartItem> items);

	public List<Order> getAllOrdersByUser(User orderer);
}
