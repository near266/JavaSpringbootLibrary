package com.example.LibraryJavaBe.BookService.InterfaceSvc;

import com.example.LibraryJavaBe.BookService.Entities.tbl_uploadfile;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadfile {
    tbl_uploadfile saveUploadFile(MultipartFile file) throws Exception;

    tbl_uploadfile getFileUpload(String fileName);
}
