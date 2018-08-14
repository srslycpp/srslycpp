package pl.srslycpp.myWeb.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pl.srslycpp.myWeb.RecipeEntity.Ingredient;
import pl.srslycpp.myWeb.commands.IngredientCommand;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        ingredientCommand.setAmount(ingredient.getAmount());

        if(ingredient.getRecipe() != null){
            System.out.println("ingredient.getRecipe().getId(): "+ingredient.getRecipe().getId());
            System.out.println("ingredientCommand.getRecipeId(): "+ingredientCommand.getRecipeId());
            System.out.println("ingredient.getId(): "+ingredient.getId());
            System.out.println("_-____--_-___-------____-----__ "+ingredient.getId());
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
            System.out.println(" ");
            System.out.println("ingredientCommand.getRecipeId(): "+ingredientCommand.getRecipeId());

        }
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        return ingredientCommand;
    }
}