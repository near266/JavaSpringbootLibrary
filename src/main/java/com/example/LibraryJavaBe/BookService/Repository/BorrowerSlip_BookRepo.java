package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.BorrowerSlip_Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerSlip_BookRepo extends JpaRepository<BorrowerSlip_Book,Long> {
}
