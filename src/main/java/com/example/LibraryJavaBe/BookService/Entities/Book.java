package com.example.LibraryJavaBe.BookService.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;



@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString

@Table(name = "books")
public class Book extends  BaseEntity{
        private String title;
        @Lob
        @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
        private String img;
        private String name;
        private String author;
        //ma sach quoc te
        private String isbn;
        //nha xuat ban
        private String publisher;

        private Integer quantityTotal;
        private Integer quantityAvailabel;

        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)

        @JoinTable(
                name = "book_category",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id")
        )
        @ToString.Exclude
        @JsonIgnore
        private Set<Category> categories;
        @OneToMany(mappedBy = "book")
        @JsonIgnore

        private Set<Book_BorrowerCard> bookCards;
        @OneToMany(mappedBy = "book")
        @JsonIgnore

        private Set<BorrowerSlip_Book> borrowerSlip_books;


}
