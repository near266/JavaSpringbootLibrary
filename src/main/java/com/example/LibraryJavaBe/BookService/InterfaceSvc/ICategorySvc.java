package com.example.LibraryJavaBe.BookService.InterfaceSvc;

import com.example.LibraryJavaBe.BookService.Entities.Book;
import com.example.LibraryJavaBe.BookService.Entities.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICategorySvc {
     Category AddCate(Category category);
     Category UpdateCate(Category category);
     Optional<List<Category>> GetAllCate();
     boolean DeleteCate(Long id);
     List<Category> getBooksByCategoryName(String categoryname);
     Category GetByid(Long id);
}
