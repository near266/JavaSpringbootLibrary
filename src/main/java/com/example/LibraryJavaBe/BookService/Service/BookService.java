package com.example.LibraryJavaBe.BookService.Service;

import com.example.LibraryJavaBe.BookService.Entities.Book;
import com.example.LibraryJavaBe.BookService.Entities.Category;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IBookSvc;
import com.example.LibraryJavaBe.BookService.Repository.BookRepo;
import com.example.LibraryJavaBe.BookService.Repository.CategoryRepo;
import com.example.LibraryJavaBe.Exception.ResourceNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookSvc {
    private  final BookRepo repo;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book AddBook(Book book) throws SQLException, IOException {

        return repo.save(book);
    }

    @Override
    public List<Book> getAllBook() {

     return repo.findAll();
      //return repo.finAllBook1();
    }

    @Override
    public byte[] getBookPhotoByBookId(Long bookId) throws SQLException {
//        Optional<Book> book = repo.findById(bookId);
//        if(book.isEmpty()){
//            throw new ResourceNotFoundException("Book not found");
//        }
//        Blob photoBlob = book.get().getImg();
//        if(photoBlob != null){
//            return photoBlob.getBytes(1, (int) photoBlob.length());
//        }
        return null;
    }

    @Override
    public boolean deleteBook(Long bookId) {

       repo.deleteById(bookId);
       return  true;
    }

    @Override
    public Book UpdateBook(Long id, Book updatedBook) throws SQLException, IOException {
        Book existingBook = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Copy non-null properties from updatedBook to existingBook
        BeanUtils.copyProperties(updatedBook, existingBook, "id");

        return repo.save(existingBook);
    }

    @Override
    public Book getBookById(Long id) {

        return repo.findById(id).orElse(null);
    }

    @Override
    public Book AddBookToCategory(Long idCate, Long idBook) {
        var book = repo.findById(idBook);

        return book.orElse(null);
    }

    @Override
    public List<Category> getCategoriesByBookId(Long bookId) {
        Optional<Book> bookOptional = repo.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return (List<Category>) book.getCategories();
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findBooksByCategoryName(String categoryName) {

        List<Book> bks= entityManager.createQuery(
                            "SELECT b FROM Book b JOIN b.categories c WHERE c.name = :categoryName", Book.class)
                    .setParameter("categoryName", categoryName)
                    .getResultList();
return  bks;
    }
}
