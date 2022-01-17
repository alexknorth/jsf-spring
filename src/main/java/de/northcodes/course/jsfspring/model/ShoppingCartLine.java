package de.northcodes.course.jsfspring.model;

import java.math.BigDecimal;

public class ShoppingCartLine {

    private Product product;
    private int quantity;
    private BigDecimal amount;

    public ShoppingCartLine(Product product) {
        this.product = product;
        this.quantity = 1;
        this.amount = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
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
