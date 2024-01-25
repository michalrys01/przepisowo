package przepisowoaplikacja.przepisowoaplikacja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // Oznacza, że klasa reprezentuje encję w bazie danych.
@Getter // Lombok: Automatycznie generuje metody get dla pól klasy.
@Setter // Lombok: Automatycznie generuje metody set dla pól klasy.
public class Recipe {

    @Id // Oznacza, że pole to jest kluczem głównym encji.
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Określa strategię generowania klucza głównego.
    private Long id; // Pole przechowujące identyfikator przepisu.

    private String title; // Pole przechowujące tytuł przepisu.

    @Column(columnDefinition="TEXT") // Określa, że kolumna w bazie danych ma przechowywać dane w formie tekstu o długości większej niż standardowe.
    private String text; // Pole przechowujące treść przepisu.

    private LocalDateTime created_time; // Pole przechowujące datę i czas utworzenia przepisu.
    private LocalDateTime edit_time; // Pole przechowujące datę i czas ostatniej edycji przepisu.

    @NotNull // Oznacza, że pole nie może być puste (null).
    @ManyToOne // Określa relację wielu do jednego (wiele przepisów może być przypisanych do jednego konta).
    @JoinColumn(name="account_id", referencedColumnName = "id", nullable = false) // Określa nazwę kolumny w bazie danych, która przechowuje identyfikator konta.
    private Account account; // Pole przechowujące informacje o koncie właściciela przepisu.

    @Override // Przesłania metodę toString z klasy Object, służy do wygenerowania reprezentacji tekstowej obiektu.
    public String toString(){
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", text='" + text + "'" +
                ", created_time='" + created_time + "'" +
                ", edit_time=" + edit_time + "}";
    }
}


//@Entity: Oznacza, że klasa reprezentuje encję w bazie danych.
//@Getter: Automatycznie generuje metody get dla pól klasy.
//@Setter: Automatycznie generuje metody set dla pól klasy.
//@Id: Oznacza, że pole to jest kluczem głównym encji.
//@GeneratedValue(strategy = GenerationType.SEQUENCE): Określa strategię generowania klucza głównego.
//@Column(columnDefinition="TEXT"): Określa, że kolumna w bazie danych ma przechowywać dane w formie tekstu o długości większej niż standardowe.
//@NotNull: Oznacza, że pole nie może być puste (null).
//@ManyToOne: Określa relację wielu do jednego (wiele przepisów może być przypisanych do jednego konta).
//@JoinColumn(name="account_id", referencedColumnName = "id", nullable = false): Określa nazwę kolumny w bazie danych, która przechowuje identyfikator konta.
//@Override: Przesłania metodę toString z klasy Object, służy do wygenerowania reprezentacji tekstowej obiektu.