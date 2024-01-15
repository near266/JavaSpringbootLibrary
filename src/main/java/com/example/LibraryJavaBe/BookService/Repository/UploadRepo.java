package com.example.LibraryJavaBe.BookService.Repository;

import com.example.LibraryJavaBe.BookService.Entities.tbl_uploadfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepo extends JpaRepository<tbl_uploadfile,Integer> {
    /*
     * Generated SQL: SELECT tbl_uploadfile.id FROM schema_uploadfile.tbl_uploadfile
     * WHERE tbl_uploadfile.file_name = ? LIMIT 1
     */
    boolean existsByfileName(String fileName);

    /*
     * Generate SQL: SELECT...FROM schema_uploadfile.tbl_uploadfile WHERE tbl_uploadfile.file_name = ?
     */
    Optional<tbl_uploadfile> findByFileName(String fileName);
}
