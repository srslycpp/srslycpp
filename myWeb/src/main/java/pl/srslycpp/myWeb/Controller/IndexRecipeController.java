package pl.srslycpp.myWeb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.srslycpp.myWeb.Service.RecipeService;

@Slf4j
@Controller
public class IndexRecipeController {

    private RecipeService recipeService;

    public IndexRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/projects/recipe/index")
    public String getIndexPage(Model model){

        
        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("some debug log");
        return "recipe/index";
    }

}