package pl.srslycpp.myWeb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.srslycpp.myWeb.RecipeRepository.RecipeRepository;
import pl.srslycpp.myWeb.Service.IngredientService;
import pl.srslycpp.myWeb.Service.RecipeService;
import pl.srslycpp.myWeb.commands.IngredientCommand;

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
    @RequestMapping("/projects/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String ingredientView(@PathVariable Long recipeId,
                                 @PathVariable Long ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));

        return "recipe/ingredients/showIngredient";
    }

    @GetMapping
    @RequestMapping("/projects/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId,
                                   @PathVariable Long ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        // tak sie nie da  // model.addAttribute("recipe", recipeService.findById(recipeId));
        return "recipe/ingredients/ingredientform";
    }
    @GetMapping
    @RequestMapping("/projects/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable Long recipeId, Model model){
        model.addAttribute("ingredient", new IngredientCommand() );
        return "recipe/ingredients/ingredientform";
    }

    @PostMapping
    @RequestMapping("/saveIngredient")
    public String saveAndUpdate(@ModelAttribute IngredientCommand ingredientCommand){

        ingredientService.saveAndUpdate(ingredientCommand);
        return "redirect:/projects/recipe/ingredients/listOfIngredients";
    }

    @GetMapping
    @RequestMapping("/projects/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId){
        //ingredientService.deleteIngredient(ingredientId);
        ingredientService.deleteIngredientByRecipeIdAndIngredientId(recipeId, ingredientId);
        System.out.println("++++");
        return "redirect:/projects/recipe/" + recipeId + "/listOfIngredients";
    }
}
