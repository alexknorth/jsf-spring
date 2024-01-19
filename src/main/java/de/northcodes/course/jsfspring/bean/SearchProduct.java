package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Product;
import de.northcodes.course.jsfspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@RequestScoped
@Component
@ManagedBean
public class SearchProduct {

    private String searchText;

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    @Autowired
    private ProductService productService;

    public List<Product> getProductsBySearchText() {
        return productService.getProductsBySearchText(searchText);
    }
}