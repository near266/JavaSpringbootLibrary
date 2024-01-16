package com.example.LibraryJavaBe.BookService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="BorrowerCard")
public class BorrowerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer UserId ;
    @OneToMany(mappedBy = "borrowerCard", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Book_BorrowerCard> bookCards;


}
