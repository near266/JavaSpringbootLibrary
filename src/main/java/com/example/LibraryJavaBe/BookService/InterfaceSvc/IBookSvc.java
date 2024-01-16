package com.example.LibraryJavaBe.BookService.InterfaceSvc;

import com.example.LibraryJavaBe.BookService.Entities.Book;
import com.example.LibraryJavaBe.BookService.Entities.Category;
import com.example.LibraryJavaBe.BookService.Entities.tbl_uploadfile;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IBookSvc {
    Book AddBook(Book book) throws SQLException, IOException;
    List<Book> getAllBook();
    byte[] getBookPhotoByBookId(Long bookId) throws SQLException;
    boolean deleteBook(Long bookId);
    Book UpdateBook(
          Long id, Book updatedBook
    ) throws SQLException, IOException;
    Book getBookById(Long id);

    Book AddBookToCategory(Long idCate ,Long idBook);
    List<Category> getCategoriesByBookId(Long bookId);
    List<Book> findBooksByCategoryName(String categoryName);


}
