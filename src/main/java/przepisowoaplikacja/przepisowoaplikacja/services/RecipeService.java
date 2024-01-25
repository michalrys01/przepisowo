package przepisowoaplikacja.przepisowoaplikacja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;
import przepisowoaplikacja.przepisowoaplikacja.repositories.RecipeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    // Serwis obsługujący operacje na encjach Recipe.

    @Autowired
    private RecipeRepository recipeRepository;  // Wstrzyknięcie repozytorium obsługującego encje Recipe.

    public Optional<Recipe> getById(Long id) {
        // Metoda do pobierania przepisu na podstawie jego identyfikatora.
        return recipeRepository.findById(id);
    }

    public List<Recipe> getAll() {
        // Metoda do pobierania listy wszystkich przepisów.
        return recipeRepository.findAll();
    }

    public Recipe save(Recipe recipe) {
        // Metoda do zapisywania lub aktualizowania przepisu.
        if (recipe.getId() == null) {
            // Ustawienie czasu utworzenia, jeśli przepis jest nowy.
            recipe.setCreated_time(LocalDateTime.now());
        }
        recipe.setEdit_time(LocalDateTime.now());  // Ustawienie czasu ostatniej edycji.
        return recipeRepository.save(recipe);  // Zapisanie przepisu w bazie danych.
    }

    public void delete(Recipe recipe) {
        // Metoda do usuwania przepisu.
        recipeRepository.delete(recipe);
    }
}


//@Service: Oznacza klasę jako komponent Springa, będący serwisem, który wykonuje logikę biznesową.
//@Autowired: Automatyczne wstrzykiwanie zależności przez Spring.
//private RecipeRepository recipeRepository;: Pole repozytorium obsługującego operacje na encjach Recipe.
//public Optional<Recipe> getById(Long id) {...}: Metoda do pobierania przepisu na podstawie identyfikatora.
//public List<Recipe> getAll() {...}: Metoda do pobierania listy wszystkich przepisów.
//public Recipe save(Recipe recipe) {...}: Metoda do zapisywania lub aktualizowania przepisu w bazie danych.
//public void delete(Recipe recipe) {...}: Metoda do usuwania przepisu z bazy danych.