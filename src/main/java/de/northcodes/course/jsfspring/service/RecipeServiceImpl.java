package de.northcodes.course.jsfspring.service;

import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return StreamSupport.stream(recipeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Recipe getRecipe(long id) {
        return recipeRepository.findById(id).get();
    }


    @Override
    public Recipe getRecipeName(String recipeName) {
        return recipeRepository.findByRecipeName(recipeName);
    }

    @Override
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public void delete(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    @Override
    public Recipe getRecipeIfUserIsOwner(long recipeId, User user) {
        return user.getRecipes().stream().filter(recipe -> recipe.getId() == recipeId).findFirst().orElse(new Recipe());
    }
}
