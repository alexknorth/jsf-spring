package de.northcodes.course.jsfspring.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.OrderState;
import de.northcodes.course.jsfspring.model.ShoppingCartItem;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.persistence.OrderRepository;
import de.northcodes.course.jsfspring.persistence.ShoppingCartItemRepository;

import javax.faces.application.FacesMessage;
import javax.persistence.EntityNotFoundException;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	ShoppingCartItemRepository shoppingCartItemRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public void orderNow(User orderer, List<ShoppingCartItem> items) {
		//Status MANUAL_VERIFICATION_REQUIRED need to be changed once the checkout process has been implemented.
		Order order = new Order(new Date(), orderer, OrderState.MANUAL_VERIFICATION_REQUIRED, null);
		Order persistedOrder = orderRepository.save(order);
		
		items.forEach(i -> i.setOrder(persistedOrder));
		shoppingCartItemRepository.saveAll(items);
	}

	@Override
	public List<Order> getAllOrdersByUser(User orderer) {
		return orderRepository.findByOrderer(orderer);
	}

	@Override
	public Order getOrderById(long id) {
		return this.orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found."));
	}

}
