package pl.srslycpp.myWeb.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


        log.debug("some debug log");
        return "recipe/index";
    }

}