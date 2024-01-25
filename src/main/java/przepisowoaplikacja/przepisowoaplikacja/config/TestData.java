package przepisowoaplikacja.przepisowoaplikacja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.models.Authority;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;
import przepisowoaplikacja.przepisowoaplikacja.repositories.AuthorityRepository;
import przepisowoaplikacja.przepisowoaplikacja.services.AccountService;
import przepisowoaplikacja.przepisowoaplikacja.services.RecipeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TestData implements CommandLineRunner {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void run(String...args) throws Exception{
        List<Recipe> recipe = recipeService.getAll();

        if(recipe.size()==0){

            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            Account account1 = new Account();
            Account account2 = new Account();
            Account account3 = new Account();
            Account account4 = new Account();


            account1.setFirstName("user");
            account1.setLastName("user");
            account1.setEmail("user@domain.com");
            account1.setPassword("user");
            Set<Authority> authorities1 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities1::add);
            account1.setAuthorities(authorities1);




            account2.setFirstName("admin");
            account2.setLastName("admin");
            account2.setEmail("admin@domain.com");
            account2.setPassword("admin");
            Set<Authority> authorities2 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities2::add);
            authorityRepository.findById("ROLE_ADMIN").ifPresent(authorities2::add);
            account2.setAuthorities(authorities2);


            account3.setFirstName("Michal");
            account3.setLastName("Rys");
            account3.setEmail("mrys2001@gmail.com");
            account3.setPassword("user");
            Set<Authority> authorities3 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities3::add);
            account3.setAuthorities(authorities3);


            account4.setFirstName("Karol");
            account4.setLastName("Wójt");
            account4.setEmail("karol@wp.pl");
            account4.setPassword("user");
            Set<Authority> authorities4 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities4::add);
            account4.setAuthorities(authorities4);


            accountService.save(account1);
            accountService.save(account2);
            accountService.save(account3);
            accountService.save(account4);


            Recipe recipe1 = new Recipe();
            recipe1.setTitle("Rosół");
            recipe1.setText("Przepis na pyszny rosół");
            recipe1.setAccount(account1);

            Recipe recipe2 = new Recipe();
            recipe2.setTitle("Kotlet schabowy");
            recipe2.setText("Przepis na polski obiad");
            recipe2.setAccount(account2);


            Recipe recipe3 = new Recipe();
            recipe3.setTitle("Makaron z serem");
            recipe3.setText("Wez makaron i wymieszaj z serem białym");
            recipe3.setAccount(account3);

            Recipe recipe4 = new Recipe();
            recipe4.setTitle("Pizza");
            recipe4.setText("Ciasto posmaruj sosem pomidorowym, dodaj ser i ulubione składniki");
            recipe4.setAccount(account4);



            recipeService.save(recipe1);
            recipeService.save(recipe2);
            recipeService.save(recipe3);
            recipeService.save(recipe4);






        }
    }

}
