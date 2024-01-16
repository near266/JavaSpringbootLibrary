package com.example.LibraryJavaBe.BookService.Service;

import com.example.LibraryJavaBe.BookService.Entities.BorrowerCard;
import com.example.LibraryJavaBe.BookService.Entities.Event;
import com.example.LibraryJavaBe.BookService.Entities.HandmadeItem;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IEventOrHandmadeSvc;
import com.example.LibraryJavaBe.BookService.Repository.cate_bookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CateBookService implements IEventOrHandmadeSvc {
    private final cate_bookRepo _repo;
    @Override
    public Event Add(Event event) {

;
        return _repo.save(event);
    }

    @Override
    public HandmadeItem Addhandmade(HandmadeItem handmadeItem) {
        return null;
    }
}
