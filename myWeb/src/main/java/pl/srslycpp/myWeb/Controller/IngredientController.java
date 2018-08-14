package pl.srslycpp.myWeb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.srslycpp.myWeb.Service.IngredientService;
import pl.srslycpp.myWeb.Service.RecipeService;
import pl.srslycpp.myWeb.Service.UnitOfMeasureService;
import pl.srslycpp.myWeb.commands.IngredientCommand;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;
    private IngredientService ingredientService;
    private UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @GetMapping("/projects/recipe/{recipeId}/listOfIngredients")
    public String viewIngredients(@PathVariable Long recipeId, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        System.out.println(" ");
        System.out.println("IngredientsController Recipe ID: >>> " + recipeId + " <<< ");

        return "recipe/ingredients/listOfIngredients";
    }


    @GetMapping("/projects/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String ingredientView(@PathVariable Long recipeId,
                                 @PathVariable Long ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));

        return "recipe/ingredients/showIngredient";
    }


    @GetMapping("/projects/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable Long recipeId,
                                   @PathVariable Long ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/ingredientform";
    }

    @GetMapping("/projects/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable Long recipeId, Model model){
        model.addAttribute("ingredient", new IngredientCommand() );
        return "recipe/ingredients/ingredientform";
    }


//    @PostMapping("/saveIngredient")
//    public String saveAndUpdate(@ModelAttribute IngredientCommand ingredientCommand){
//        System.out.println("saveAndUpdate IN IngredientsController getRecipeId: "+ ingredientCommand.getRecipeId());
//        IngredientCommand savedIngredientCommand = ingredientService.saveAndUpdate(ingredientCommand);
//        return "redirect:/projects/recipe/index";
//    }
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);
        System.out.println("saveAndUpdate IN IngredientsController getRecipeId: "+ ingredientCommand.getRecipeId());
        System.out.println("saveAndUpdate IN IngredientsController getUnitOfMeasure: "+ ingredientCommand.getUnitOfMeasure());
        System.out.println("saveAndUpdate IN IngredientsController saved.getRecipeId: "+ savedCommand.getRecipeId());
        System.out.println("saveAndUpdate IN IngredientsController saved.getUnitOfMeasure: "+ savedCommand.getUnitOfMeasure());

        return "redirect:/projects/recipe/" + ingredientCommand.getRecipeId() + "/ingredients/" + savedCommand.getId() + "/showIngredient";
    }


    @GetMapping("/projects/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId){
        //ingredientService.deleteIngredient(ingredientId);
        ingredientService.deleteIngredientByRecipeIdAndIngredientId(recipeId, ingredientId);
        System.out.println("++++");
        return "redirect:/projects/recipe/" + recipeId + "/listOfIngredients";
    }
}
