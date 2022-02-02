package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.model.User;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Recipe getRecipe(long id);

    Recipe getRecipeName(String recipeName);

    void save(Recipe recipe);

    void delete(Recipe recipe);

    Recipe getRecipeIfUserIsOwner(long recipeId, User user);
}
