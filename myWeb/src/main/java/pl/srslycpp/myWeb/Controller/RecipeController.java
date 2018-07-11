package pl.srslycpp.myWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.srslycpp.myWeb.Service.RecipeService;

@Controller
public class RecipeController {

    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/projects/recipe/{id}")
    String getById(Model model, @PathVariable("id") Long id){
        model.addAttribute("recipe", recipeService.findById(id));
        System.out.println("Id "+ id);
        return "recipe/show";
    }
}
