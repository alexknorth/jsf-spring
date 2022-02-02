package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.JsfSpringApplication;
import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Date;

@ViewScoped
@Component
@ManagedBean
public class RecipeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RecipeService recipeService;

	private long recipeId;

	private de.northcodes.course.jsfspring.bean.readmodel.Recipe recipe;

	private de.northcodes.course.jsfspring.model.Recipe recipeDetails;

	public Recipe getRecipeDetails() {
		return recipeDetails;
	}

	public void setRecipeDetails(Recipe recipeDetails) {
		this.recipeDetails = recipeDetails;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void onload() {
		recipe = new de.northcodes.course.jsfspring.bean.readmodel.Recipe();
		recipeDetails = new de.northcodes.course.jsfspring.model.Recipe();

		if (userManager.isSignedIn()) {
			recipeDetails = recipeService.getRecipeIfUserIsOwner(recipeId, userManager.getCurrentUser());
			recipe = new de.northcodes.course.jsfspring.bean.readmodel.Recipe(recipeDetails, true);
		}
	}

	public String submit() {
		recipeDetails.setCreator(userManager.getCurrentUser());
		recipeDetails.setCreateDate(new Date());

		recipeService.save(recipeDetails);
		return "index";
	}

	public String delete() {
		recipeService.delete(recipeDetails);
		return "index";
	}

	public void validateCreateDate(FacesContext context, UIComponent component, Object value) {
		System.out.println(java.time.LocalDate.now());
	}
}
