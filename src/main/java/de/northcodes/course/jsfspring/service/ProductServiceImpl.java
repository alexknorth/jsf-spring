package de.northcodes.course.jsfspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.persistence.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getPopularProducts() {
		//log.info("getPopularProducts called: " + productRepository.findAll().iterator().next().toString());
		return StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id).get();
	}
}
