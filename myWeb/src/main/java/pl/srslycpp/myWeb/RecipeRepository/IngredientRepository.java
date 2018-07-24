package pl.srslycpp.myWeb.RecipeRepository;

import org.springframework.data.repository.CrudRepository;
import pl.srslycpp.myWeb.RecipeEntity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
