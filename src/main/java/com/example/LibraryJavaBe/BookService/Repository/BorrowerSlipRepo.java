package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.BorrowerSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerSlipRepo extends JpaRepository<BorrowerSlip,Long> {

}
