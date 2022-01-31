package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@RequestScoped
@Component
@ManagedBean
public class AllRecipes {

	@Autowired
	private RecipeService recipeService;

	private static final Logger log = LoggerFactory.getLogger(AllRecipes.class);

	public List<Recipe> getRecipes() {
		log.info("Get All Recipes");
		recipeService.getAllRecipes().forEach(recipe -> log.info(recipe.toString()));
		return recipeService.getAllRecipes();
	}
}
