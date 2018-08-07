package pl.srslycpp.myWeb.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.srslycpp.myWeb.RecipeEntity.Ingredient;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;
import pl.srslycpp.myWeb.RecipeRepository.IngredientRepository;
import pl.srslycpp.myWeb.RecipeRepository.RecipeRepository;
import pl.srslycpp.myWeb.commands.IngredientCommand;
import pl.srslycpp.myWeb.converters.IngredientCommandToIngredient;
import pl.srslycpp.myWeb.converters.IngredientToIngredientCommand;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository,
                                 IngredientRepository ingredientRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientRepository = ingredientRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
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

        if(!ingredientCommandOptional.isPresent()){
            //todo error handling
        }
        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveAndUpdate(IngredientCommand command) {
        Ingredient detachedIngredient = ingredientCommandToIngredient.convert(command);

        Ingredient savedIngredient = ingredientRepository.save(detachedIngredient);

        return ingredientToIngredientCommand.convert(savedIngredient);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long idToDelete) {
        Optional<Recipe> findedRecipe = recipeRepository.findById(recipeId);

        Recipe recipe = findedRecipe.get();
        System.out.println("recipe.getId(): "+ recipe.getId());
        System.out.println("idToDelete: "+ idToDelete);
        Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(idToDelete))
                .findFirst();
        if(ingredientOptional.isPresent()){
            System.out.println(ingredientOptional.isPresent());
            Ingredient ingredientToDelete = ingredientOptional.get();
            ingredientToDelete.setRecipe(null);
            System.out.println("ingredientToDelete.getRecipe(): " + ingredientToDelete.getRecipe());
            recipeRepository.save(recipe);
        }
    }
}
