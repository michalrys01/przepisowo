package przepisowoaplikacja.przepisowoaplikacja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;
import przepisowoaplikacja.przepisowoaplikacja.services.AccountService;
import przepisowoaplikacja.przepisowoaplikacja.services.RecipeService;

import java.util.List;

@Component
public class TestData implements CommandLineRunner {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String...args) throws Exception{
        List<Recipe> recipe = recipeService.getAll();

        if(recipe.size()==0){

            Account account1 = new Account();
            Account account2 = new Account();

            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user@domain.com");
            account1.setPassword("user");
            account1.setUsername("user");

            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@domain.com");
            account2.setPassword("admin");
            account2.setUsername("admin");

            accountService.save(account1);
            accountService.save(account2);

            Recipe recipe1 = new Recipe();
            recipe1.setTitle("Rosół");
            recipe1.setText("Przepis na pyszny rosół");
            recipe1.setAccount(account1);

            Recipe recipe2 = new Recipe();
            recipe2.setTitle("Kotlet schabowy");
            recipe2.setText("Przepis na polski obiad");
            recipe2.setAccount(account2);
            recipeService.save(recipe1);
            recipeService.save(recipe2);
        }
    }

}
