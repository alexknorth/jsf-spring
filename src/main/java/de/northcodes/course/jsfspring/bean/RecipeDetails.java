package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

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

		if (userManager.isSignedIn()) {
			recipe = new de.northcodes.course.jsfspring.bean.readmodel.Recipe(recipeService.getRecipeIfUserIsOwner(recipeId, userManager.getCurrentUser()), true);
		}
	}

	//public String submit() {return recipeManager.save(recipe);}
	
	public void validateCreateDate(FacesContext context, UIComponent component, Object value) {
		System.out.println(java.time.LocalDate.now());
	}
}
