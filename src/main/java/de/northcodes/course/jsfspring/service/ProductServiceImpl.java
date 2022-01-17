package de.northcodes.course.jsfspring.service;

import org.springframework.stereotype.Service;

import de.northcodes.course.jsfspring.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private final Map<Long, Product> products;

    private final List<Product> popularProducts;

    public ProductServiceImpl() {
        Map<Long, Product> map = new HashMap<>();
        map.put(1L, new Product(1L, "Microphone", "Essential for every vocalist - this microphone makes your voice sound great. Suitable for any kind of music and any voice.", new BigDecimal("95.00"), "microphone"));
        map.put(2L, new Product(2L, "Guitar", "This guitar sounds great and looks cool. Rock, blues or jazz, this guitar does it all.", new BigDecimal("995.00"), "guitar"));
        map.put(3L, new Product(3L, "Saxophone", "Steal the show with this cool saxophone. Suitable for beginners as well as for advanced players.", new BigDecimal("1195.00"), "saxophone"));
        map.put(4L, new Product(4L, "Bass Guitar", "Every band needs a solid bass guitar. This one will never let you down.", new BigDecimal("895.00"), "bassguitar"));
        map.put(5L, new Product(5L, "Drum Kit", "This complete drum kit provides everything a drummer needs. Including an extra pair of sticks.", new BigDecimal("1249.00"), "drumkit"));

        products = Collections.unmodifiableMap(map);
        popularProducts = Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    @Override
    public List<Product> getPopularProducts() {
        return popularProducts;
    }

    @Override
    public Product getProduct(long id) {
        return products.get(id);
    }
}
