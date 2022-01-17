package de.northcodes.course.jsfspring.bean;

import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.service.ProductService;

import java.io.Serializable;
import javax.annotation.ManagedBean;

@Component
@ViewScoped
@ManagedBean
public class ProductDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductService productService;

    private long productId;

    private Product product;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void onload() {
        product = productService.getProduct(productId);
    }

    public Product getProduct() {
        return product;
    }
}
