package com.example.LibraryJavaBe.BookService.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Category")

public class Category extends BaseEntity{
    private String name;

    @ManyToMany(mappedBy = "categories" ,fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Book> books;

}
