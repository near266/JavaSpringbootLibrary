package com.example.LibraryJavaBe.BookService.Entities;

import jakarta.persistence.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "HandmadeItem")
public class HandmadeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private String itemName;
    private  Double price;
    private  Integer quantity;
    private String imgUrl;
}
