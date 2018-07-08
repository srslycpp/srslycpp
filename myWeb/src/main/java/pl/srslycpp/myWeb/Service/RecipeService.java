package pl.srslycpp.myWeb.Service;

import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;

import java.util.Set;


@Service
public interface RecipeService  {
     Set<Recipe> getRecipes();
}
