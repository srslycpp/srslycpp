package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;
import pl.srslycpp.myWeb.commands.RecipeCommand;

import java.util.Set;


@Service
public interface RecipeService  {
     Set<Recipe> getRecipes();
     Recipe findById(Long id);
     RecipeCommand saveRecipeCommand(RecipeCommand command);
     RecipeCommand findCommandById(Long command);
     void deleteById(Long id);
}
