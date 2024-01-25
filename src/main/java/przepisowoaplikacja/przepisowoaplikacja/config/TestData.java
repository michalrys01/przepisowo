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

//Ten kod to Spring Boot CommandLineRunner, który jest uruchamiany automatycznie przy starcie aplikacji i wypełnia bazę danych danymi testowymi, jeśli nie ma jeszcze żadnych przepisów.
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
        // Pobierz wszystkie przepisy z serwisu
        List<Recipe> recipe = recipeService.getAll();
        // Jeśli nie ma przepisów, wypełnij bazę danych danymi testowymi
        if(recipe.size()==0){

            // Tworzenie ról uprawnień (USER i ADMIN)
            Authority user = new Authority();
            user.setName("ROLE_USER");
            authorityRepository.save(user);

            Authority admin = new Authority();
            admin.setName("ROLE_ADMIN");
            authorityRepository.save(admin);

            // Tworzenie testowych kont użytkowników

            Account account1 = new Account();
            Account account2 = new Account();
            Account account3 = new Account();
            Account account4 = new Account();
            Account account5 = new Account();
            Account account6 = new Account();


            // Ustawianie szczegółów użytkownika
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


            account4.setFirstName("Ania");
            account4.setLastName("Wójtowicz");
            account4.setEmail("ania22@wp.pl");
            account4.setPassword("user");
            Set<Authority> authorities4 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities4::add);
            account4.setAuthorities(authorities4);

            account5.setFirstName("Paulina");
            account5.setLastName("Rola");
            account5.setEmail("paula@wp.pl");
            account5.setPassword("user");
            Set<Authority> authorities5 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities5::add);
            account5.setAuthorities(authorities5);


            account6.setFirstName("Sebastian");
            account6.setLastName("Murek");
            account6.setEmail("seba@wp.pl");
            account6.setPassword("user");
            Set<Authority> authorities6 = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities6::add);
            account6.setAuthorities(authorities6);

            // Zapisywanie utworzonych kont za pomocą accountService

            accountService.save(account5);
            accountService.save(account2);
            accountService.save(account3);
            accountService.save(account4);
            accountService.save(account6);

            // Tworzenie testowych przepisów

            Recipe recipe1 = new Recipe();

            // Ustawianie szczegółów przepisu, w tym tytułu, tekstu i powiązanie go z kontem (account5 w tym przypadku)


            recipe1.setTitle("Rosół");
            recipe1.setText("PRZYGOTOWANIE\n" +
                    "Jeśli mamy całego kurczaka należy go pokroić na części - odciąć szyję, skrzydełka, uda. Korpus pozostawić razem z piersiami.\n" +
                    "Wszystkie części kurczaka oraz wołowinę i podroby opłukać, następnie włożyć do dużego garnka i zalać zimną wodą. Posolić i zagotować na średnim ogniu.\n" +
                    "Po zagotowaniu zmniejszyć ogień i zszumować wywar. Zmniejszyć ogień i gotować na małym ogniu (rosół ma tylko \"mrugać\") pod lekko uchyloną pokrywą przez około 1 i 1/2 godziny.\n" +
                    "W międzyczasie przygotować warzywa: marchewkę, pietruszkę i selera obrać (lub dla większego aromatu - tylko dokładnie umyć). Cebulę, pora i natkę opłukać (cebuli nie obierać, jej łuski nadadzą zupie ładnego koloru).\n" +
                    "Warzywa włożyć do rosołu i zagotować. Dodać przyprawy: ziele angielskie, liść laurowy i czarny pieprz. Zmniejszyć ogień i gotować na małym ogniu przez około 1 godzinę lub do 1 i 1/2 godziny.\n" +
                    "Rosół podawać gorący, z ugotowanym makaronem, posiekaną natką, pokrojoną na cienkie plasterki marchewką z rosołu oraz kawałkami mięsa z kurczaka.\n" +
                    "WSKAZÓWKI\n" +
                    "Pyszny rosół można ugotować tylko z kurczaka już bez wołowiny i podrobów. Wystarczy dodać trochę mniej wody.\n" +
                    "Pozostały rosół należy przecedzić, zagotować i zamknąć w słoikach. Po ostudzeniu wstawić do lodówki. Mięso i warzywa z rosołu przechowujemy oddzielnie.\n" +
                    "Następnego dnia z reszty rosołu można przygotować ZUPĘ POMIDOROWĄ.\n" +
                    "Ugotowane mięso z rosołu można wykorzystać do: farszu na KROKIETY, PIEROGI Z MIĘSEM, PASZTECIKI.");
            recipe1.setAccount(account5);

            Recipe recipe2 = new Recipe();
            recipe2.setTitle("Kotlet schabowy");
            recipe2.setText("PRZYGOTOWANIE\n" +
                    "Ostrym nożem odciąć białą otoczkę z żyłki po zewnętrznej części mięsa. Pokroić na 4 plastry. Położyć na desce i dokładnie roztłuc na cieniutkie filety (mogą wyjść duże, wielkości pół talerza). Do rozbicia mięsa najlepiej użyć specjalnego tłuczka z metalowym obiciem z wytłoczoną krateczką.\n" +
                    "Filety namoczyć w mleku z dodatkiem soli i pieprzu (ewentualnie z dodatkiem kilku plastrów cebuli) przez ok. 2 godziny lub dłużej jeśli mamy czas (można też zostawić do namoczenia na noc).\n" +
                    "Wyjąć filety z mleka i osuszyć je papierowymi ręcznikami. Doprawić solą (niezbyt dużo, bo zalewa z mleka była już solona) i pieprzem, obtoczyć w mące, następnie w roztrzepanym jajku, a na koniec w bułce tartej.\n" +
                    "Na patelni rozgrzać klarowane masło (ok. 1/2 cm warstwa) lub smalec. Smażyć partiami po 2 kotlety, na większym ogniu, po 2 minuty z każdej strony. Następnie zmniejszyć ogień i smażyć jeszcze po ok. 3 minuty z każdej strony. Przetrzeć patelnię papierowym ręcznikiem i powtórzyć z kolejną partią, na świeżym tłuszczu.\n" +
                    "Usmażone schabowe odsączyć z tłuszczu na papierowym ręczniku i podawać z ziemniakami i kapustą lub mizerią.\n" +
                    "PROPOZYCJA PODANIA\n" +
                    "puree ziemniaczane / ziemniaki z wody\n" +
                    "\n" +
                    "KAPUSTA ZASMAŻANA / SURÓWKA Z KISZONEJ KAPUSTY / MIZERIA\n" +
                    "\n" +
                    "WSKAZÓWKI\n" +
                    "Kupując mięso na dziale mięsnym, możemy poprosić o pokrojenie schabu i wystekowanie (rozbicie) w maszynie.");
            recipe2.setAccount(account6);


            Recipe recipe3 = new Recipe();
            recipe3.setTitle("Makaron z serem");
            recipe3.setText("Większość z nas miała okazję w czasie swojego dzieciństwa spróbować makaronu z serem na słodko. Ta nieskomplikowana potrawa z pewnością zachęci do jedzenia nawet największego niejadka. Dorosłym z kolei przypadnie do gustu jako ciekawa alternatywa dla tradycyjnych słodyczy.\n" +
                    "\n" +
                    "Poza tym makaron z serem na słodko to jedno z tych dań, które wpisuje się w modny nurt „zero waste”. Możesz wykorzystać do tego dowolny makaron, jaki został po innym obiedzie oraz biały ser – wystarczy 30 dekagramów.\n" +
                    "\n" +
                    "W misce rozgnieć i wymieszaj biały ser, śmietanę, cukier i Cukier Wanilinowy WINIARY. Po ugotowaniu makaronu zgodnie z instrukcją na opakowaniu odcedź go i wymieszaj z serem. Danie smakuje zarówno na ciepło i na zimno. Idealnie nada się do niego makaron świderki, ale z powodzeniem możesz wykorzystać też penne, wstążki, czy rotelle o ciekawym kształcie, który szczególnie przypadnie do gustu dzieciom.\n" +
                    "\n" +
                    "Wśród niewątpliwych zalet tego dania należy wymienić łatwość jego przygotowania. Nie zajmuje to więcej niż 10-15 minut. Poza tym możesz wykorzystać do niego ulubione dodatki. Ser możesz urozmaicić np. odrobiną cynamonu albo kawałkami owoców. Świetnie sprawdzą się borówki, truskawki bądź pokrojone w kostkę jabłko.");
            recipe3.setAccount(account3);

            Recipe recipe4 = new Recipe();
            recipe4.setTitle("Pizza");
            recipe4.setText("PRZYGOTOWANIE\n" +
                    "Świeże drożdże ocieplić w temperaturze pokojowej. Przygotować rozczyn: drożdże rozpuścić w ciepłej wodzie, dodać 2 łyżki mąki oraz cukier, dokładnie wymieszać i odstawić na ok. 10 minut do wyrośnięcia (rozczyn ze świeżych drożdży zwiększa objętość o ok. 3 razy - jeśli tak się nie stanie proces przygotowania rozczynu trzeba powtórzyć od nowa, natomiast rozczyn z drożdży instant może się tylko trochę spienić).\n" +
                    "Mąkę przesiać do miski, wymieszać z solą, zrobić wgłębienie w środku i wlać w nie rozczyn. Sukcesywnie zagarniać łyżką mąkę do środka i przez 2 - 3 minuty mieszać składniki, pod koniec dodając jeszcze oliwę.\n" +
                    "Połączone składniki wyłożyć na stolnicę oprószoną mąką. Wyrabiać przez ok. 15 minut aż ciasto będzie elastyczne i gładkie (ciasto można też zagnieść mikserem planetarnym).\n" +
                    "Wyrobione ciasto włożyć do dużej miski, przykryć ściereczką i odstawić na ok. 1 godzinę do wyrośnięcia.\n" +
                    "Wyrośnięte ciasto wyjąć na stolnicę i chwilę pozagniatać. Podzielić na 2 części, uformować z nich kulki i odłożyć na ok. 7 minut pod ściereczką.\n" +
                    "Blaszki (tortownice) posmarować oliwą. Włożyć na środek kulkę ciasta, delikatnie spłaszczyć i rozciągać, rozprowadzając palcami po całej powierzchni dna, zaczynając od środka i zostawiając niewielki \"wałeczek\" na brzegu (zob. zdjęcia poniżej). UWAGA: najlepiej robić to kilkoma etapami, ciasto na początku sprężynuje i \"cofa się\" ale jeśli odczekamy chwilę będziemy mogli je dalej rozciągać.\n" +
                    "Wyłożyć cienką warstwę SOSU POMIDOROWEGO, ser* oraz ulubione dodatki. Odczekać ok. 15 minut aż ciasto podrośnie, następnie piec w maksymalnie nagrzanym piekarniku (min. 250 st. C) przez ok. 10 minut.\n" +
                    "WSKAZÓWKI\n" +
                    "* Polecam połączenie pół na pół mozzarelli świeżej (z zalewy, w kulce) oraz suchej (tartej).");
            recipe4.setAccount(account4);

            // Zapisywanie utworzonych przepisów za pomocą recipeService


            recipeService.save(recipe1);
            recipeService.save(recipe2);
            recipeService.save(recipe3);
            recipeService.save(recipe4);






        }
    }

}
