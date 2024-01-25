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
    // Wstrzyknięcie serwisu obsługującego przepisy
    @Autowired
    private RecipeService recipeService;
    // Obsługa żądania GET na główną stronę ("/")
    @GetMapping("/")
    public String home(Model model){
        // Pobranie wszystkich przepisów z serwisu
       List<Recipe> recipe = recipeService.getAll();
        // Dodanie listy przepisów do modelu, aby można było użyć jej w widoku
        model.addAttribute("recipe", recipe);
        // Zwrócenie nazwy widoku (home.html) do wyrenderowania przez Spring MVC
        return "home";
    }




}
//@Controller: Oznacza klasę jako kontroler w Spring MVC.
//@Autowired: Automatyczne wstrzyknięcie zależności, w tym przypadku serwisu obsługującego przepisy (RecipeService).
//@GetMapping("/"): Oznacza, że ta metoda obsługuje żądania HTTP GET na główną stronę ("/").
//public String home(Model model): Metoda obsługująca żądanie, przyjmująca obiekt Model do przekazania danych do widoku i zwracająca nazwę widoku.
//List<Recipe> recipes = recipeService.getAll();: Pobranie wszystkich przepisów z serwisu przy użyciu RecipeService.
//model.addAttribute("recipe", recipes);: Dodanie listy przepisów do modelu pod kluczem "recipe", aby była dostępna w widoku.
//return "home";: Zwrócenie nazwy widoku, który ma zostać użyty do wyrenderowania strony. W tym przypadku nazwa to "home".