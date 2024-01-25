package przepisowoaplikacja.przepisowoaplikacja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import przepisowoaplikacja.przepisowoaplikacja.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    // Interfejs AuthorityRepository dziedziczy po JpaRepository, co umożliwia korzystanie z gotowych operacji związanych z bazą danych.

    // JpaRepository<Authority, String>: Dziedziczenie z JpaRepository, gdzie 'Authority' to encja, a 'String' to typ klucza głównego (name w klasie Authority).

    // Pusty interfejs: Skoro dziedziczymy po JpaRepository, nie musimy deklarować własnych metod, ponieważ wiele operacji (np. dodawanie, usuwanie, szukanie) jest już dostępnych.
}

//@Repository: Oznacza interfejs jako komponent Springa, co umożliwia automatyczną obsługę przez Spring Data.
//interface AuthorityRepository extends JpaRepository<Authority, String>: Dziedziczenie po JpaRepository pozwala na korzystanie z gotowych operacji bazodanowych dla encji Authority.
//JpaRepository<Authority, String>: Określa, że encją jest Authority, a klucz główny ma typ String.
//Brak własnych metod: W tym przypadku nie ma potrzeby dodawania dodatkowych metod, ponieważ większość operacji związanych z encją Authority jest już dostępna dzięki JpaRepository.