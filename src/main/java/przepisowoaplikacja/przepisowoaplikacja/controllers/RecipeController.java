package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


    @PostMapping("/recipes/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@PathVariable Long id, Recipe recipe, BindingResult result, Model model){
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if(optionalRecipe.isPresent()){
            Recipe existingRecipe = optionalRecipe.get();

            existingRecipe.setTitle(recipe.getTitle());
            existingRecipe.setText(recipe.getText());
            recipeService.save(existingRecipe);
        }
        return "redirect:/recipes/" + recipe.getId();
    }

    @GetMapping("/recipes/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model) {

        // find recipe by id
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        // if recipe exist put it in model
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe_edit";
        } else {
            return "404";
        }
    }





    @GetMapping("/recipes/{id}/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePost(@PathVariable Long id) {

        // find recipe by id
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            recipeService.delete(recipe);
            return "redirect:/";
        } else {
            return "404";
        }
    }

}
