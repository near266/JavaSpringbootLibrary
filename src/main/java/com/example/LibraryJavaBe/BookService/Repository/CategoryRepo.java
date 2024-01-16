package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CategoryRepo  extends JpaRepository<Category,Long> {
List<Category> findByName(String name);
}
