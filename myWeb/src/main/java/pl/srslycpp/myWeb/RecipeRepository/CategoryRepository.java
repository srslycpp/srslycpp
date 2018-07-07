package pl.srslycpp.myWeb.RecipeRepository;

import org.springframework.data.repository.CrudRepository;
import pl.srslycpp.myWeb.RecipeEntity.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
