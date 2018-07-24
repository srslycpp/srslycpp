package pl.srslycpp.myWeb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.srslycpp.myWeb.RecipeRepository.RecipeRepository;
import pl.srslycpp.myWeb.Service.IngredientService;
import pl.srslycpp.myWeb.Service.RecipeService;

@Slf4j
@Controller
public class IngredientsController {

    private RecipeRepository recipeRepository;
    private RecipeService recipeService;
    private IngredientService ingredientService;

//    public IngredientsController(RecipeRepository recipeRepository) {
//        this.recipeRepository = recipeRepository;
//    }

    public IngredientsController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/projects/recipe/{recipeId}/listOfIngredients")
    public String editIngredients(@PathVariable Long recipeId, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

//               model.addAttribute("recipe", recipeRepository.findById(recipeId).get());
        System.out.println(">>> " + recipeId + " <<< ");
        return "recipe/ingredients/listOfIngredients";
    }

    @GetMapping
    @RequestMapping("/projects/recipe/{recipeId}/ingredient/{id}/show")
    public String ingredientView(@PathVariable Long recipeId,
                                 @PathVariable Long id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        return "recipe/ingredients/showIngredient";
    }
}
