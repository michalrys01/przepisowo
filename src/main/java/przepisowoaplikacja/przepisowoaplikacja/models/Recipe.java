package przepisowoaplikacja.przepisowoaplikacja.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String text;

    private LocalDateTime created_time;


    @NotNull
    @ManyToOne
    @JoinColumn(name="account_id", referencedColumnName = "id",nullable = false)
    private Account account;

}
