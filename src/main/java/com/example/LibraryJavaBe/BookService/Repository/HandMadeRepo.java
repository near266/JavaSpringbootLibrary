package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.HandmadeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandMadeRepo extends JpaRepository<HandmadeItem,Integer> {
}
