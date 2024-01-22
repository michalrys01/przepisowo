package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;
import przepisowoaplikacja.przepisowoaplikacja.services.RecipeService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/")
    public String home(Model model){
       List<Recipe> recipe = recipeService.getAll();
       model.addAttribute("recipe", recipe);
       return "home";
    }




}
