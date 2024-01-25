package przepisowoaplikacja.przepisowoaplikacja.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.repositories.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {
    // Serwis do obsługi operacji związanych z kontem użytkownika.

    @Autowired
    private AccountRepository accountRepository;  // Wstrzyknięcie repozytorium konta użytkownika.

    @Autowired
    private PasswordEncoder passwordEncoder;  // Wstrzyknięcie enkodera hasła.

    // Metoda do zapisywania konta użytkownika.
    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));  // Zabezpieczenie hasła przed zapisem do bazy danych poprzez jego zakodowanie.
        return accountRepository.save(account);  // Wywołanie metody save z repozytorium do zapisu konta w bazie danych.
    }

    // Metoda do wyszukiwania konta użytkownika po adresie e-mail.
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);  // Wywołanie metody findByEmail z repozytorium do znalezienia konta po adresie e-mail.
    }
}


//@Service: Oznacza klasę jako komponent Springa, co umożliwia automatyczną obsługę przez mechanizmy wstrzykiwania zależności.
//private AccountRepository accountRepository;: Pole repozytorium konta użytkownika, wstrzykiwane przez Spring.
//private PasswordEncoder passwordEncoder;: Pole enkodera hasła, wstrzykiwane przez Spring.
//public Account save(Account account) {...}: Metoda służąca do zapisywania konta użytkownika w bazie danych.
//account.setPassword(passwordEncoder.encode(account.getPassword()));: Zabezpieczenie hasła przed zapisem do bazy danych przez jego zakodowanie.
//return accountRepository.save(account);: Wywołanie metody save z repozytorium do zapisu konta w bazie danych.
//public Optional<Account> findByEmail(String email) {...}: Metoda do wyszukiwania konta użytkownika po adresie e-mail.
//return accountRepository.findByEmail(email);: Wywołanie metody findByEmail z repozytorium do znalezienia konta po adresie e-mail.