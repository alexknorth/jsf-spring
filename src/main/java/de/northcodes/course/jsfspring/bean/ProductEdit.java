package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
@ManagedBean
public class ProductEdit implements Serializable {

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

    public void onLoad() {
        product = productService.getProduct(productId);
    }

    public Product getProduct() {
        return product;
    }

    public boolean update(){
        return productService.update(productId, product);
    }
}
