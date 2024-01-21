package com.example.LibraryJavaBe.Response;

import com.example.LibraryJavaBe.BookService.Entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.Resource;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class bookResponse {
    private Long Id;

    private String title;
    private String name;
    private String img;
    private String author;
    //ma sach quoc te
    private String isbn;
    //nha xuat ban
    private String publisher;
    private Integer quantityTotal;

    private Integer quantityAvailabel;


    private LocalDateTime createdAt;


    private LocalDateTime updatedAt;
   List<String> categoryName;





}

