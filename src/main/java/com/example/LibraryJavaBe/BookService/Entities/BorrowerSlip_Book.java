package com.example.LibraryJavaBe.BookService.Entities;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BorrowerSlip_Book")
public class BorrowerSlip_Book {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "borrowerSlipId")
    private BorrowerSlip borrowerSlip;

    @ManyToOne
    @JoinColumn(name = "offBorrowerSlipId")
    private OffBorrowerSlip offBorrowerSlip;

    private int quantity;

}
