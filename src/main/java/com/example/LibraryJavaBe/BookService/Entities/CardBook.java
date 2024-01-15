package com.example.LibraryJavaBe.BookService.Entities;
;
import com.example.LibraryJavaBe.UserService.Entites.User;
import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "LibraryCards")
public class CardBook {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "card_id")
        private Long cardId;

        @ManyToOne
        @JoinColumn(name = "member_id")
        private User member;

        @Column(name = "issued_date")
        private LocalDate issuedDate;

        @Column(name = "expiry_date")
        private LocalDate expiryDate;

        // Getters and setters
        // Constructors
        // Other necessary methods


}
