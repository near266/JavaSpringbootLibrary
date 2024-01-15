package com.example.LibraryJavaBe.Request;

import com.example.LibraryJavaBe.BookService.Entities.Category;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class AddBookrq {
    private  Long Id;
    private String title;

   private MultipartFile img;
    private String author;
    //ma sach quoc te
    private String isbn;
    //nha xuat ban
    private String publisher;
    private Double price;



}
