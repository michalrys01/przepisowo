package przepisowoaplikacja.przepisowoaplikacja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import przepisowoaplikacja.przepisowoaplikacja.models.Account;
import przepisowoaplikacja.przepisowoaplikacja.models.Recipe;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // Interfejs RecipeRepository dziedziczy po JpaRepository, co umożliwia korzystanie z gotowych operacji związanych z bazą danych.

    // JpaRepository<Recipe, Long>: Dziedziczenie z JpaRepository, gdzie 'Recipe' to encja, a 'Long' to typ klucza głównego (id w klasie Recipe).

    // Pusty interfejs: Skoro dziedziczymy po JpaRepository, nie musimy deklarować własnych metod, ponieważ wiele operacji (np. dodawanie, usuwanie, szukanie) jest już dostępnych.

    // W przypadku specyficznych operacji na bazie danych, można dodawać własne metody do tego interfejsu, a Spring Data automatycznie dostarczy implementację.
}

//@Repository: Oznacza interfejs jako komponent Springa, co umożliwia automatyczną obsługę przez Spring Data.
//interface RecipeRepository extends JpaRepository<Recipe, Long>: Dziedziczenie po JpaRepository pozwala na korzystanie z gotowych operacji bazodanowych dla encji Recipe.
//JpaRepository<Recipe, Long>: Określa, że encją jest Recipe, a klucz główny ma typ Long.
//Brak własnych metod: W tym przypadku nie ma potrzeby dodawania dodatkowych metod, ponieważ większość operacji związanych z encją Recipe jest już dostępna dzięki JpaRepository.