package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String getRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe";
        } else {
            return "404";
        }
    }

    @GetMapping("/recipes/new")
    public String createNewRecipe(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Account> optionalAccount = accountService.findByEmail(userDetails.getUsername());
        if (optionalAccount.isPresent()) {
            Recipe recipe = new Recipe();
            recipe.setAccount(optionalAccount.get());
            model.addAttribute("recipe", recipe);
            return "recipe_new";
        } else {
            return "404";
        }
    }


    @PostMapping("/recipes/new")
    public String saveNewRecipe(@ModelAttribute Recipe recipe) {
        recipeService.save(recipe);
        return "redirect:/recipes/" + recipe.getId();
    }

    @PostMapping("/recipes/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Recipe recipe, BindingResult result, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe existingRecipe = optionalRecipe.get();

            // Sprawdź, czy użytkownik jest właścicielem przepisu lub administratorem
            if (existingRecipe.getAccount().getEmail().equals(userDetails.getUsername()) || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                existingRecipe.setTitle(recipe.getTitle());
                existingRecipe.setText(recipe.getText());
                recipeService.save(existingRecipe);
            } else {
                return "403"; // Możesz utworzyć stronę z informacją o braku uprawnień
            }
        }
        return "redirect:/recipes/" + recipe.getId();
    }


    @GetMapping("/recipes/{id}/edit")
    public String getPostForEdit(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            // Sprawdź, czy użytkownik jest właścicielem przepisu lub administratorem
            if (recipe.getAccount().getEmail().equals(userDetails.getUsername()) || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                model.addAttribute("recipe", recipe);
                return "recipe_edit";
            } else {
                return "redirect:/"; // Przekieruj na stronę startową, jeśli użytkownik nie jest właścicielem przepisu ani administratorem
            }
        } else {
            return "404";
        }
    }



    @GetMapping("/recipes/{id}/delete")
    public String deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        Optional<Recipe> optionalRecipe = recipeService.getById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();

            // Sprawdź, czy użytkownik jest właścicielem przepisu lub administratorem
            if (recipe.getAccount().getEmail().equals(userDetails.getUsername()) || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                recipeService.delete(recipe);
                return "redirect:/";
            } else {
                model.addAttribute("error", "Nie masz uprawnień do usunięcia tego przepisu.");
                return "redirect:/"; // Przekieruj do strony głównej z komunikatem o braku uprawnień
            }
        } else {
            model.addAttribute("error", "Przepis o podanym identyfikatorze nie istnieje.");
            return "redirect:/"; // Przekieruj do strony głównej z komunikatem o nieistniejącym przepisie
        }
    }

}
