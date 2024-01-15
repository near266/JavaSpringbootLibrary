package com.example.LibraryJavaBe.BookService.Service;

import com.example.LibraryJavaBe.BookService.Entities.Cate_Book;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IBookCateSvc;
import com.example.LibraryJavaBe.BookService.Repository.CategoryRepo;
import com.example.LibraryJavaBe.BookService.Repository.cate_bookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CateBookService implements IBookCateSvc {
    private final cate_bookRepo _repo;
    @Override
    public Cate_Book Add(Cate_Book cateBook) {

;
        return _repo.save(cateBook);
    }
}
