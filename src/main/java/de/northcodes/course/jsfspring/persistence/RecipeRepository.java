package de.northcodes.course.jsfspring.persistence;

import de.northcodes.course.jsfspring.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	public Recipe findByRecipeName(String recipeName);
}