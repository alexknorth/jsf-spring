package de.northcodes.course.jsfspring.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = AbstractEntity.SHOP_PREFIX + "product")
public final class Product extends AbstractEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    @Column(name = "image_name", nullable = false)
    private String imageName;

    protected Product() {}
    
    public Product(String name, String description, BigDecimal price, String imageName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        int i = description.indexOf('.');
        if (i >= 0 && i < 100) {
            return description.substring(0, i + 1);
        } else {
            return description.substring(0, Math.min(description.length(), 100)) + "...";
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageName() {
        return imageName;
    }
    
    @Override
    public String toString() {
    	return "Product ID: " + this.getId() + ", name: " + this.getName() +", description: " + this.getDescription();  
    }
}
