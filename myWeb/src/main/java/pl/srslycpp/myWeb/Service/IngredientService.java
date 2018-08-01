package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.commands.IngredientCommand;

@Service
public interface IngredientService {

     IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
     IngredientCommand saveAndUpdate(IngredientCommand command);
     void deleteIngredient(Long id);
     void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
