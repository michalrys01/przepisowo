package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.services.AccountService;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    // Metoda obsługująca żądanie GET na wyświetlenie formularza rejestracji
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        // Tworzy nowy obiekt klasy Account do przechowywania danych formularza
        Account account = new Account();
        // Przekazuje ten obiekt do modelu, aby można go było użyć w formularzu na stronie "register"
        model.addAttribute("account", account);
        // Zwraca nazwę widoku strony rejestracji
        return "register";
    }

    // Metoda obsługująca żądanie POST na zarejestrowanie nowego użytkownika
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account account) {
        // Zapisuje nowego użytkownika w bazie danych za pomocą serwisu AccountService
        accountService.save(account);
        // Przekierowuje użytkownika na stronę główną po udanej rejestracji
        return "redirect:/";
    }
}

//@Controller: Oznacza klasę jako kontroler w Spring MVC.
//@Autowired: Automatycznie wstrzykuje zależność do serwisu AccountService.
//@GetMapping("/register"): Oznacza, że ta metoda obsługuje żądania HTTP GET na stronę rejestracji ("/register").
//public String getRegisterPage(Model model): Metoda obsługująca żądanie GET, zwraca nazwę widoku strony rejestracji.
//@ModelAttribute Account account: Oznacza, że parametr account zostanie wstrzyknięty z danych przesłanych w formularzu.
//        model.addAttribute("account", account): Dodaje obiekt account do modelu, aby można go było użyć w formularzu na stronie "register".
//@PostMapping("/register"): Oznacza, że ta metoda obsługuje żądania HTTP POST na stronę rejestracji ("/register").
//public String registerNewUser(@ModelAttribute Account account): Metoda obsługująca żądanie POST, zapisuje nowego użytkownika w bazie danych.
//return "redirect:/";: Przekierowuje użytkownika na stronę główną po udanej rejestracji.