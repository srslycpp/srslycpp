package pl.srslycpp.myWeb.RecipeRepository;

import org.springframework.data.repository.CrudRepository;
import pl.srslycpp.myWeb.RecipeEntity.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);


}
