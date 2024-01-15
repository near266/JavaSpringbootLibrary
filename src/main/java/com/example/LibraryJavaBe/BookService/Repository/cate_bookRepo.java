package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.CardBook;
import com.example.LibraryJavaBe.BookService.Entities.Cate_Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cate_bookRepo extends JpaRepository<Cate_Book,Long> {

}
