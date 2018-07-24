package pl.srslycpp.myWeb.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;
import pl.srslycpp.myWeb.RecipeRepository.RecipeRepository;
import pl.srslycpp.myWeb.commands.IngredientCommand;
import pl.srslycpp.myWeb.converters.IngredientToIngredientCommand;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(!optionalRecipe.isPresent()){

            //todo error handling
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> ingredientCommandOptional= recipe.getIngredients().stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).
                map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(ingredientCommandOptional.isPresent()){
            //todo error handling
        }
        return ingredientCommandOptional.get();
    }
}
