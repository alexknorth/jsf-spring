package de.northcodes.course.jsfspring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "order")
public class Order extends AbstractEntity{

	@Column(name = "order_date", nullable = false)
	private Date orderDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="fk_orderer",referencedColumnName="id",nullable=false,unique=false)
	private User orderer;

	@Column(name = "order_state", nullable = false)
	private OrderState orderState;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ShoppingCartItem> shoppingCartItems;
    
	protected Order() {
	}

	public Order(Date orderDate, User orderer, OrderState orderState, List<ShoppingCartItem> shoppingCartItems) {
		this.orderDate = orderDate;
		this.orderer = orderer;
		this.orderState = orderState;
		this.shoppingCartItems = shoppingCartItems;
	}


	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public User getOrderer() {
		return orderer;
	}

	public void setOrderer(User orderer) {
		this.orderer = orderer;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

}
