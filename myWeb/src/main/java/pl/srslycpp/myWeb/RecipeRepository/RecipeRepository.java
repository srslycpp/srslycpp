package pl.srslycpp.myWeb.RecipeRepository;

import org.springframework.data.repository.CrudRepository;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;

import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Optional<Recipe> findByDescription(String description);
}
