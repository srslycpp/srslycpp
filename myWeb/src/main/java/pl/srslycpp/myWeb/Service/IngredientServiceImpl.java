package pl.srslycpp.myWeb.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.srslycpp.myWeb.RecipeEntity.Ingredient;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;
import pl.srslycpp.myWeb.RecipeRepository.RecipeRepository;
import pl.srslycpp.myWeb.RecipeRepository.UnitOfMeasureRepository;
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
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if(!optionalRecipe.isPresent()){

            //todo error handling
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> ingredientCommandOptional= recipe.getIngredients()
                .stream().filter(ingredient -> ingredient.getId().equals(ingredientId)).
                map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo error handling
        }
        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand ) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
        System.out.println("recipeOptional.get().getId() "+recipeOptional.get().getId());
        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + ingredientCommand.getRecipeId());
            System.out.println("jakis tam if ");
            return new IngredientCommand();

        } else {
            Recipe recipe = recipeOptional.get();
            System.out.println("jakis tam else under Recipe recipe = recipeOptional.get()");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();
            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();

                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                System.out.println("jakis tam if "+ ingredientCommand.getUnitOfMeasure().getId()+" "
                        + ingredientCommand.getUnitOfMeasure().getDescription());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                .findById(ingredientCommand.getUnitOfMeasure().getId())     //NullPointerException
                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
                System.out.println("jakis tam if "+ ingredientCommand.getUnitOfMeasure().getId()+" "
                        + ingredientCommand.getUnitOfMeasure().getDescription());
            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            //check by description
            if(!savedIngredientOptional.isPresent()){
                //not totally safe... But best guess
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientCommand
                                .getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }

    }
//
//    @Override
//    public void deleteIngredient(Long id) {
//        ingredientRepository.deleteById(id);
//    }

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
