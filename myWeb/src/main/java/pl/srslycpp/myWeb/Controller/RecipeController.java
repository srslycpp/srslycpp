package pl.srslycpp.myWeb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.srslycpp.myWeb.Service.RecipeService;
import pl.srslycpp.myWeb.Service.RecipeServiceImpl;

@Slf4j
@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/projects/recipe/index")
    String getIndexPage(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());
        model.addAttribute("ingredients", recipeService.getRecipes());


        log.debug("some debug log");
        return "recipe/index";
    }

    @RequestMapping("/projects/recipe/{id}")
    String getById(Model model, @PathVariable("id") Long id){
        model.addAttribute("recipe", recipeService.getRecipesById(id));
        System.out.println("Id "+ id);
        return "recipe/show";
    }

}