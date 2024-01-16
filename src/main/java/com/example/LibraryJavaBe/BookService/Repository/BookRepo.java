package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.Book;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {

//    void delete(Optional<Book> check);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.name = :categoryName")
    List<Book> findBooksByCategoryName(@Param("categoryName") String categoryName);
}
