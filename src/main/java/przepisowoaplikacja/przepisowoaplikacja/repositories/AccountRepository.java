package przepisowoaplikacja.przepisowoaplikacja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    // Interfejs AccountRepository dziedziczy po JpaRepository, co umożliwia korzystanie z gotowych operacji związanych z bazą danych.

    // Metoda findByEmail: Automatycznie generuje zapytanie SQL do bazy danych, szukając konta o podanym adresie email.
    // Optional<Account>: Zamiast zwracać obiekt, który może być null, używamy Optional, co sygnalizuje, że wynik może być pusty.
}


//@Repository: Oznacza interfejs jako komponent Springa, co umożliwia automatyczną obsługę przez Spring Data.
//interface AccountRepository extends JpaRepository<Account, Long>: Dziedziczenie po JpaRepository pozwala na korzystanie z gotowych operacji bazodanowych dla encji Account.
//Optional<Account> findByEmail(String email);: Deklaracja metody, która automatycznie generuje zapytanie SQL do bazy danych, szukając konta o podanym adresie email. Używa typu Optional dla bezpiecznej obsługi przypadku, gdy konto nie zostanie znalezione.