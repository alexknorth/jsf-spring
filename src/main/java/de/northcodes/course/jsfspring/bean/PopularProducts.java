package de.northcodes.course.jsfspring.bean;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.service.ProductService;

import java.util.List;

@RequestScoped
@Component
@ManagedBean
public class PopularProducts {

	@Autowired
	private ProductService productService;

	public List<Product> getProducts() {
		return productService.getPopularProducts();
	}
}
