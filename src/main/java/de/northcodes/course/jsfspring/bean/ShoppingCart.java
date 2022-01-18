package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Order;
import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.model.ShoppingCartItem;
import de.northcodes.course.jsfspring.service.OrderService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SessionScoped
@Component
@ManagedBean
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<ShoppingCartItem> items = new ArrayList<>();

    private int totalQuantity = 0;

    private BigDecimal totalAmount = BigDecimal.ZERO;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    OrderService orderService;

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void addProduct(Product product) {
    	System.out.println("ShoppingCart.addProduct called");
        // Find the item for this product, increment quantity and amount if found
        for (ShoppingCartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.incrementQuantityAndAmount();
                incrementTotalQuantityAndAmount(item.getProduct().getPrice());
                return;
            }
        }

        // No item for this product yet, add a new item
        items.add(new ShoppingCartItem(product));
        incrementTotalQuantityAndAmount(product.getPrice());
    }

    public void removeProduct(Product product) {
        Iterator<ShoppingCartItem> it = items.iterator();
        while (it.hasNext()) {
            ShoppingCartItem item = it.next();

            // If this is the item for this product, decrement quantity and amount;
            // remove the item if the quantity has become 0
            if (item.getProduct().getId() == product.getId()) {
                if (item.decrementQuantityAndAmount()) {
                    it.remove();
                }

                decrementTotalQuantityAndAmount(product.getPrice());
            }
        }
    }

    private void incrementTotalQuantityAndAmount(BigDecimal productPrice) {
        totalQuantity++;
        totalAmount = totalAmount.add(productPrice);
    }

    private void decrementTotalQuantityAndAmount(BigDecimal productPrice) {
        totalQuantity--;
        totalAmount = totalAmount.subtract(productPrice);
    }
    
    public void orderNow() {
    	orderService.orderNow(userManager.getCurrentUser(), items);
    	totalQuantity = 0;
    	totalAmount = BigDecimal.ZERO;
    	items.removeAll(items);
    }
    
    public List<Order> getAllOrders() {
        return orderService.getAllOrdersByUser(userManager.getCurrentUser());
    }
}
