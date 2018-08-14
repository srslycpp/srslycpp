package pl.srslycpp.myWeb.Service.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.srslycpp.myWeb.Controller.IngredientController;
import pl.srslycpp.myWeb.Service.IngredientService;
import pl.srslycpp.myWeb.Service.RecipeService;
import pl.srslycpp.myWeb.Service.UnitOfMeasureService;
import pl.srslycpp.myWeb.commands.IngredientCommand;
import pl.srslycpp.myWeb.commands.RecipeCommand;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {

    @Mock
    IngredientService ingredientService;

    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController ingredientsController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientsController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientsController).build();
    }

    @Test
    public void testListIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/projects/recipe/1/listOfIngredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/listOfIngredients"))
                .andExpect(model().attributeExists("recipe"));
        //then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }


    @Test
    public void testShowIngredient() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        //when

        mockMvc.perform(get("/projects/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/showIngredient"))
                .andExpect(model().attributeExists("ingredient"));
    }
}