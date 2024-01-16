package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.BorrowerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerCardRepo extends JpaRepository<BorrowerCard,Long> {
}
