package pl.srslycpp.myWeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.srslycpp.myWeb.Service.RecipeService;
import pl.srslycpp.myWeb.commands.RecipeCommand;

@Controller
public class RecipeController {

  RecipeService recipeService;
  
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/projects/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @GetMapping("/projects/recipe/{id}/show")
    public String getRecipeById(Model model, @PathVariable("id") Long id){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/show";
    }

    @GetMapping("/projects/recipe/{id}/update")
    public String update(Model model, @PathVariable("id") Long id){
        model.addAttribute("recipe", recipeService.findById(id));
        return "recipe/recipeform";
    }


    @PostMapping("/recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(command);

        return "redirect:/projects/recipe/"+savedRecipe.getId()+ "/show";
    }

    @RequestMapping("/projects/recipe/{id}/delete")
    public String delete(@PathVariable Long id){
        recipeService.deleteById(id);
        return "redirect:/projects/recipe/index";
    }





















    









//    RecipeService recipeService;
//
//    public RecipeController(RecipeService recipeService) {
//        this.recipeService = recipeService;
//    }
//
//    @RequestMapping("/projects/recipe/{id}/show")
//    String getById(Model model, @PathVariable("id") String id){
//        model.addAttribute("recipe", recipeService.findById(new Long(id)));
//        System.out.println("Id "+ id);
//        return "recipe/show";
//    }
//    @RequestMapping("/projects/recipe/new")
//    String newRecipe(Model model){
//        model.addAttribute("recipe", new RecipeCommand());
//        return "recipe/recipeform";
//    }
//
//    @RequestMapping("/projects/recipe/{id}/update")
//    public String update(Model model, @PathVariable("id") Long id){
//        model.addAttribute("recipe", recipeService.findById(id));
//        return "recipe/recipeform";
//    }
//
//    @PostMapping
//    @RequestMapping("recipe")
//    String saveOrUpadate(@ModelAttribute RecipeCommand command){
//        RecipeCommand saveRecipe = recipeService.saveRecipeCommand(command);
//
//        return "redirect:/projects/recipe/" + saveRecipe.getId()+"/show";
//    }
}
