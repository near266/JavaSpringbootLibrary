package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.DeliveryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryInfoRepo extends JpaRepository<DeliveryInfo,Integer> {
}
