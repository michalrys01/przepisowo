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

    @Autowired
    private RecipeRepository recipeRepository;

    public Optional<Recipe> getById(Long id){
        return recipeRepository.findById(id);
    }
    public List<Recipe> getAll(){
        return recipeRepository.findAll();

    }

    public Recipe save(Recipe recipe){
        if(recipe.getId()==null){
            recipe.setCreated_time(LocalDateTime.now());
        }
        return recipeRepository.save(recipe);
    }

}
