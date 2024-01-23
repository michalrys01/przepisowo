package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;
import przepisowoaplikacja.przepisowoaplikacja.services.AccountService;
import przepisowoaplikacja.przepisowoaplikacja.services.RecipeService;

import java.util.Optional;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/recipes/{id}")
    public String getRecipie(@PathVariable Long id, Model model){
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if(optionalRecipe.isPresent()){
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        }else{
            return "404";
        }
    }

    @GetMapping("/recipes/new")
    public String createNewRecipe(Model model){
        Optional<Account> optionalAccount = accountService.findByEmail("user@domain.com");
        if (optionalAccount.isPresent()){
            Recipe recipe = new Recipe();
            recipe.setAccount(optionalAccount.get());
            model.addAttribute("recipe", recipe);
            return "recipe_new";
        }else{
            return "404";
        }
    }

    @PostMapping("/recipes/new")
    public String saveNewRecipe(@ModelAttribute Recipe recipe){
        recipeService.save(recipe);
        return "redirect:/recipes/"+recipe.getId();
    }




}
