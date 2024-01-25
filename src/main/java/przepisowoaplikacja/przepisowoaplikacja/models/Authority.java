package przepisowoaplikacja.przepisowoaplikacja.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity // Oznacza, że klasa reprezentuje encję w bazie danych.
@Getter // Lombok: Automatycznie generuje metody get dla pól klasy.
@Setter // Lombok: Automatycznie generuje metody set dla pól klasy.
@NoArgsConstructor // Lombok: Automatycznie generuje konstruktor bezargumentowy.
public class Authority implements Serializable {

    @Id // Oznacza, że pole to jest kluczem głównym encji.
    @Column(length = 16) // Określa długość kolumny w bazie danych.
    private String name; // Pole przechowujące nazwę roli.

    @Override // Przesłania metodę toString z klasy Object, służy do wygenerowania reprezentacji tekstowej obiektu.
    public String toString(){
        return "Authority{" + "name='" + name + "'" + "}";
    }
}


//@Entity: Oznacza, że klasa reprezentuje encję w bazie danych.
//@Getter: Automatycznie generuje metody get dla pól klasy.
//@Setter: Automatycznie generuje metody set dla pól klasy.
//@NoArgsConstructor: Automatycznie generuje konstruktor bezargumentowy.
//@Id: Oznacza, że pole to jest kluczem głównym encji.
//@Column(length = 16): Określa długość kolumny w bazie danych.
//@Override: Przesłania metodę toString z klasy Object, służy do wygenerowania reprezentacji tekstowej obiektu.