package com.example.LibraryJavaBe.BookService.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString

@Table(name = "books")
public class Book extends  BaseEntity{
        private String title;
        @Lob
        @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
        private String img;
        private String author;
        //ma sach quoc te
        private String isbn;
        //nha xuat ban
        private String publisher;
        private Double price;
        @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)

        @JoinTable(
                name = "book_category",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id")
        )
        @ToString.Exclude
        @JsonIgnore
        private Set<Category> categories;


}
