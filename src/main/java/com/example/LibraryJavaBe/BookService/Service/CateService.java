package com.example.LibraryJavaBe.BookService.Service;

import com.example.LibraryJavaBe.BookService.Entities.Book;
import com.example.LibraryJavaBe.BookService.Entities.Category;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.ICategorySvc;
import com.example.LibraryJavaBe.BookService.Repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CateService implements ICategorySvc {
    private  final CategoryRepo _repo;
    @Override
    public Category AddCate(Category category) {
        var cate=_repo.save(category);
        return cate;
    }

    @Override
    public Category UpdateCate(Category category) {
        var check = _repo.findById(category.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        BeanUtils.copyProperties(category, check, "id");
        return _repo.save(check);
    }

    @Override
    public Optional<List<Category>> GetAllCate() {
        return Optional.of(_repo.findAll());
    }

    @Override
    public boolean DeleteCate(Long id) {
        var check = _repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        _repo.delete(check);
        return true;
    }

    @Override
    public Set<Book> getBooksByCategoryName(String categoryname) {
        Category category = _repo.findByName(categoryname);
        return  null;
    }

    @Override
    public Category GetByid(Long id) {
        Category cate= _repo.findById(id).orElse(null);
        return  cate;
    }
}
