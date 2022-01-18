package de.northcodes.course.jsfspring.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "shopping_cart_item")
public class ShoppingCartItem extends AbstractEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_product",referencedColumnName="id",nullable=false,unique=false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="fk_order",referencedColumnName="id",nullable=false,unique=false)
	private Order order;
	
	private int quantity;
	private BigDecimal amount;

	public ShoppingCartItem(Product product) {
		this.product = product;
		this.quantity = 1;
		this.amount = product.getPrice();
	}


	
	
	public Product getProduct() {
		return product;
	}




	public void setProduct(Product product) {
		this.product = product;
	}




	public Order getOrder() {
		return order;
	}




	public void setOrder(Order order) {
		this.order = order;
	}




	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	public BigDecimal getAmount() {
		return amount;
	}




	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}




	public void incrementQuantityAndAmount() {
		quantity++;
		amount = amount.add(product.getPrice());
	}

	public boolean decrementQuantityAndAmount() {
		quantity--;
		amount = amount.subtract(product.getPrice());
		return quantity == 0;
	}
}
