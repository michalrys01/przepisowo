package przepisowoaplikacja.przepisowoaplikacja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
