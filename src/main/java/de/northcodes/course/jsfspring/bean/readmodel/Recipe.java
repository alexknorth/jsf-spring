package de.northcodes.course.jsfspring.bean.readmodel;

public class Recipe extends de.northcodes.course.jsfspring.model.Recipe {
    private boolean detailsVisible;

    public Recipe() {}

    public boolean getDetailsVisible() {
        return detailsVisible;
    }

    public void setDetailsVisible(boolean detailsVisible) {
        this.detailsVisible = detailsVisible;
    }

    public Recipe(de.northcodes.course.jsfspring.model.Recipe dataRecipe) {
        super(dataRecipe.getRecipeName(), dataRecipe.getPortion(), dataRecipe.getIngredients(), dataRecipe.getPreparation(), dataRecipe.getCookingTime(), dataRecipe.getCreateDate(), dataRecipe.getCreator());
    }

    public Recipe(de.northcodes.course.jsfspring.model.Recipe dataRecipe, boolean detailsVisible) {
        super(dataRecipe.getRecipeName(), dataRecipe.getPortion(), dataRecipe.getIngredients(), dataRecipe.getPreparation(), dataRecipe.getCookingTime(), dataRecipe.getCreateDate(), dataRecipe.getCreator());
        this.detailsVisible = detailsVisible;
    }
}
