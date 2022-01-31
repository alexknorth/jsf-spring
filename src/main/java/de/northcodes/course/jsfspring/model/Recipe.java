package de.northcodes.course.jsfspring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "recipe_name", nullable = false, unique = true)
    private String recipeName;

    @Column(name = "portion", nullable = true)
    private Number portion;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "preparation", nullable = false)
    private String preparation;


    @Column(name = "cooking_time", nullable = false)
    private String cookingTime;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="fk_creator",referencedColumnName="id",nullable=false,unique=false)
    private User creator;


    public Recipe() {
    }

    public Recipe(String recipeName, Number portion, String ingredients, String preparation, String cookingTime, Date createDate, User creator) {
        this.recipeName = recipeName;
        this.portion = portion;
        this.preparation = preparation;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.createDate = createDate;
        this.creator = creator;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Number getPortion() {
        return portion;
    }

    public void setPortion(Number portion) {
        this.portion = portion;
    }

    public String getIngredients() {return ingredients;}

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {return preparation;}

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public long getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public String toString () { return this.id + ", " + this.recipeName;}
}
