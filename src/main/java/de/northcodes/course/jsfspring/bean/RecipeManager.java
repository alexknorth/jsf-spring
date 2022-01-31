package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
@Component
@ManagedBean
public class RecipeManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RecipeService recipeService;

    private Recipe currentRecipe;

    public boolean isSignedIn() {
        return currentRecipe != null;
    }

    public Recipe getCurrentRecipe() {
        return currentRecipe;
    }

    public String save(Recipe recipe) {
        recipeService.saveRecipe(recipe);
        currentRecipe = recipe;
        return "index";
    }
}
