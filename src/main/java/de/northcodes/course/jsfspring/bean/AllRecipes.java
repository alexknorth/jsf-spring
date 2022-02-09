package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Component
@ManagedBean
public class AllRecipes {

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private UserManager userManager;

	private static final Logger log = LoggerFactory.getLogger(AllRecipes.class);

	public List<de.northcodes.course.jsfspring.bean.readmodel.Recipe> getAllRecipes() {
		if (userManager.isSignedIn()) {
			return recipeService.getAllRecipes()
					.stream()
					.map(recipe -> new de.northcodes.course.jsfspring.bean.readmodel.Recipe(recipe, recipe.getCreator().getId() == userManager.getCurrentUser().getId()))
					.collect(Collectors.toList());
		} else {
			return recipeService.getAllRecipes()
					.stream()
					.map(recipe -> new de.northcodes.course.jsfspring.bean.readmodel.Recipe(recipe,false))
					.collect(Collectors.toList());
		}
	}
}
