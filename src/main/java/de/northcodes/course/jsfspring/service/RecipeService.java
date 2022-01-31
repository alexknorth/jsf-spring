package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();

    Recipe getRecipe(long id);

    Recipe getRecipeName(String recipeName);

    void saveRecipe(Recipe recipe);

    public Recipe getRecipeIfUserIsOwner(long recipeId, long userId);
