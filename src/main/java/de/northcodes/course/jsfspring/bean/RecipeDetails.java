package de.northcodes.course.jsfspring.bean;

import de.northcodes.course.jsfspring.JsfSpringApplication;
import de.northcodes.course.jsfspring.model.Recipe;
import de.northcodes.course.jsfspring.model.User;
import de.northcodes.course.jsfspring.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Date;

@ViewScoped
@Component
@ManagedBean
public class RecipeDetails implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(JsfSpringApplication.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private RecipeService recipeService;

	private long recipeId;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
		type = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
		String recipeIdString = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("recipeId");
		if (recipeIdString == null){
			recipeId = 0;
		} else {
			recipeId = Long.valueOf(recipeIdString);
		}

		recipe = new de.northcodes.course.jsfspring.bean.readmodel.Recipe();
		recipeDetails = new de.northcodes.course.jsfspring.model.Recipe();

		if (userManager.isSignedIn() && (type.equals("read") || type.equals("create"))) {
			recipeDetails = recipeService.getRecipeIfUserIsOwner(recipeId, userManager.getCurrentUser());
			recipe = new de.northcodes.course.jsfspring.bean.readmodel.Recipe(recipeDetails, true);
		}
	}

	public boolean isEditable() {
		return !type.equals("read");
	}

	public void makeEditable() {
		type = "create";
	}

	public String submit() {
		recipeDetails.setCreator(userManager.getCurrentUser());
		recipeDetails.setCreateDate(new Date());

		recipeService.save(recipeDetails);
		return "index";
	}

	public String delete() {
		User userCreator = recipeDetails.getCreator();
		userCreator.getRecipes().remove(recipeDetails);
		recipeService.delete(recipeDetails);
		return "index";
	}
}
