package com.example.LibraryJavaBe.BookService.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OffBorrowerSlip")
public class OffBorrowerSlip  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;
private Integer stateId;
private String name;
private String phoneNumber;
private LocalDate Date;
    @OneToMany(mappedBy = "offBorrowerSlip")
    private Set<BorrowerSlip_Book> borrowerSlip_books;
}
