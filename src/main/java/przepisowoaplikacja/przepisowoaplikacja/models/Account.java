package przepisowoaplikacja.przepisowoaplikacja.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // Oznacza, że klasa reprezentuje encję w bazie danych.
@Getter // Lombok: Automatycznie generuje metody get dla pól klasy.
@Setter // Lombok: Automatycznie generuje metody set dla pól klasy.
@NoArgsConstructor // Lombok: Automatycznie generuje konstruktor bezargumentowy.
public class Account {

    @Id // Oznacza, że pole to jest kluczem głównym encji.
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Określa strategię generowania wartości dla klucza głównego (np. SEQUENCE).
    private Long id;

    private String email; // Pole przechowujące adres e-mail użytkownika.

    private String password; // Pole przechowujące hasło użytkownika.
    private String firstName; // Pole przechowujące imię użytkownika.
    private String lastName; // Pole przechowujące nazwisko użytkownika.

    @OneToMany(mappedBy = "account") // Określa relację jeden-do-wielu z encją Recipe, gdzie to pole jest strona posiadającą.
    private List<Recipe> recipes; // Lista przepisów przypisanych do danego konta.

    @ManyToMany(fetch = FetchType.EAGER) // Określa relację wiele-do-wielu z encją Authority, pobierając dane natychmiast (EAGER).
    @JoinTable(name = "account_authority", // Określa nazwę tabeli łączącej te dwie encje.
            joinColumns = {@JoinColumn(name="account_id", referencedColumnName = "id")}, // Określa kolumny związane z obecną encją (Account).
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName ="name")}) // Określa kolumny związane z drugą encją (Authority).
    private Set<Authority> authorities = new HashSet<>(); // Zbiór ról przypisanych do danego konta.

    @Override // Przesłania metodę toString z klasy Object, służy do wygenerowania reprezentacji tekstowej obiektu.
    public String toString(){
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", authorities=" + authorities + '}';
    }
}


//@Entity: Oznacza, że klasa reprezentuje encję w bazie danych.
//@Getter: Automatycznie generuje metody get dla pól klasy.
//@Setter: Automatycznie generuje metody set dla pól klasy.
//@NoArgsConstructor: Automatycznie generuje konstruktor bezargumentowy.
//@Id: Oznacza, że pole to jest kluczem głównym encji.
//@GeneratedValue(strategy = GenerationType.SEQUENCE): Określa strategię generowania wartości dla klucza głównego (np. SEQUENCE).
//@OneToMany(mappedBy = "account"): Określa relację jeden-do-wielu z encją Recipe, gdzie to pole jest strona posiadającą.
//@ManyToMany(fetch = FetchType.EAGER): Określa relację wiele-do-wielu z encją Authority, pobierając dane natychmiast (EAGER).
//@JoinTable(name = "account_authority", ...): Określa nazwę tabeli łączącej te dwie encje i konfigurację kolumn łączących.
//@Override: Przesłania metodę toString z klasy Object, służy do wygenerowania reprezentacji tekstowej obiektu.