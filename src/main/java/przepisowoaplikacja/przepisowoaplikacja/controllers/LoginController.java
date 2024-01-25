package przepisowoaplikacja.przepisowoaplikacja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Obsługa żądania GET na stronę logowania ("/login")
    @GetMapping("/login")
    public String getLoginPage() {
        // Zwraca nazwę widoku, który ma zostać użyty do wyrenderowania strony logowania (login.html)
        return "login";
    }
}
//@Controller: Oznacza klasę jako kontroler w Spring MVC.
//@GetMapping("/login"): Oznacza, że ta metoda obsługuje żądania HTTP GET na stronę logowania ("/login").
//public String getLoginPage(): Metoda obsługująca żądanie, zwracająca nazwę widoku strony logowania.
//return "login";: Zwraca nazwę widoku, który ma zostać użyty do wyrenderowania strony logowania. W tym przypadku nazwa to "login".