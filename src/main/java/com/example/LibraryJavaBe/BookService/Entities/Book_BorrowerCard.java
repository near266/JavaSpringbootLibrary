package com.example.LibraryJavaBe.BookService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book_BorrowerCard")
public class Book_BorrowerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_card_id")
    private Integer bookCardId;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

    @ManyToOne

    @JoinColumn(name = "borrower_card_id")
    @JsonIgnore
    private BorrowerCard borrowerCard;

    private Integer quantity;
}
