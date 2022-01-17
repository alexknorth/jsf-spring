package de.northcodes.course.jsfspring.service;

import java.util.List;

import de.northcodes.course.jsfspring.model.Product;

public interface ProductService {

    List<Product> getPopularProducts();

    Product getProduct(long id);
}
