package pl.srslycpp.myWeb.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.srslycpp.myWeb.RecipeEntity.Category;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;
import pl.srslycpp.myWeb.commands.RecipeCommand;


@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand,
                                 NotesToNotesCommand notesToNotesCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipeSource) {
        if (recipeSource == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeSource.getId());
        recipeCommand.setCookTime(recipeSource.getCookTime());
        recipeCommand.setPrepTime(recipeSource.getPrepTime());
        recipeCommand.setDescription(recipeSource.getDescription());
        recipeCommand.setDifficulty(recipeSource.getDifficulty());
        recipeCommand.setDirections(recipeSource.getDirections());
        recipeCommand.setServings(recipeSource.getServings());
        recipeCommand.setSource(recipeSource.getSource());
        recipeCommand.setUrl(recipeSource.getUrl());
        recipeCommand.setNotes(notesToNotesCommand.convert(recipeSource.getNotes()));

        if (recipeSource.getCategories() != null && recipeSource.getCategories().size() > 0){
            recipeSource.getCategories()
                    .forEach((Category category) -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
        }

        if (recipeSource.getIngredients() != null && recipeSource.getIngredients().size() > 0){
            recipeSource.getIngredients()
                    .forEach(ingredient -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
        }

        return recipeCommand;
    }
}
