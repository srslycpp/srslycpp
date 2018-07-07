package pl.srslycpp.myWeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    @RequestMapping("/projects/recipe/index")
    String getIndexPage(){
        return "recipe/index";
    }

}