package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.commands.IngredientCommand;

@Service
public interface IngredientService {

     IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
     IngredientCommand saveIngredientCommand(IngredientCommand command);
//     void deleteById(Long recipeId, Long ingerdientId);
     void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
