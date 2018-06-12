package pl.srslycpp.myWeb.App;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/homepage").setViewName("homepage");
        registry.addViewController("/projects/quiz/editQuestion").setViewName("editQuestion");
        registry.addViewController("/projects/quiz/addQuestion").setViewName("addQuestion");
        registry.addViewController("/projects/quiz/allQuestion").setViewName("allQuestion");
        registry.addViewController("/projects/quiz/oneQuestion").setViewName("oneQuestion");
        registry.addViewController("/projects").setViewName("projects");

        registry.addViewController("/login").setViewName("login");
    }

}