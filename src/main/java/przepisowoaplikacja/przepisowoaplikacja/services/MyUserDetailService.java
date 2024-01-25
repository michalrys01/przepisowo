package przepisowoaplikacja.przepisowoaplikacja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    // Serwis obsługujący dostarczanie informacji o użytkownikach do mechanizmów Spring Security.

    @Autowired
    private AccountService accountService;  // Wstrzyknięcie serwisu obsługującego operacje na kontach użytkowników.

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Metoda wymagana przez interfejs UserDetailsService, odpowiedzialna za wczytywanie danych użytkownika na podstawie adresu e-mail.

        Optional<Account> optionalAccount = accountService.findByEmail(email);  // Wyszukanie konta użytkownika po adresie e-mail.
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Account not found");  // Rzucenie wyjątku, jeśli konto nie zostało znalezione.
        }
        Account account = optionalAccount.get();  // Pobranie znalezionego konta.

        // Konwersja uprawnień użytkownika na listę obiektów typu GrantedAuthority wymaganą przez Spring Security.
        List<GrantedAuthority> grantedAuthorities = account
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());

        // Zwrócenie obiektu UserDetails, reprezentującego użytkownika w systemie Spring Security.
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), grantedAuthorities);
    }
}


//@Component("userDetailsService"): Oznacza klasę jako komponent Springa, który może być automatycznie zidentyfikowany przez mechanizmy wstrzykiwania zależności.
//private AccountService accountService;: Pole serwisu obsługującego operacje na kontach użytkowników, wstrzykiwane przez Spring.
//@Override: Oznacza, że metoda jest nadpisywana z interfejsu, w tym przypadku z UserDetailsService.
//public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {...}: Metoda do wczytywania danych użytkownika na podstawie adresu e-mail.
//Optional<Account> optionalAccount = accountService.findByEmail(email);: Wyszukanie konta użytkownika po adresie e-mail.
//throw new UsernameNotFoundException("Account not found");: Rzucenie wyjątku, jeśli konto nie zostało znalezione.
//List<GrantedAuthority> grantedAuthorities = ...: Konwersja uprawnień użytkownika na listę obiektów GrantedAuthority.
//return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), grantedAuthorities);: Zwrócenie obiektu UserDetails, reprezentującego użytkownika w systemie Spring Security.