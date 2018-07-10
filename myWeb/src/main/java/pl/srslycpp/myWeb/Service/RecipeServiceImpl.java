package pl.srslycpp.myWeb.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.srslycpp.myWeb.RecipeEntity.Recipe;
import pl.srslycpp.myWeb.RecipeRepository.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe getRecipesById(Long id) {
        return recipeRepository.findById(id).get();

    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found");
        }
        return recipeOptional.get();
    }

}
