package com.example.LibraryJavaBe.BookService.Service;

import com.example.LibraryJavaBe.BookService.Entities.tbl_uploadfile;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IBookSvc;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IUploadfile;
import com.example.LibraryJavaBe.BookService.Repository.UploadRepo;
import com.example.LibraryJavaBe.Exception.FileNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService implements IUploadfile {
    private final UploadRepo uploadFileRepository;


    @Override
    public tbl_uploadfile saveUploadFile(MultipartFile file) throws Exception {
        if (uploadFileRepository.existsByfileName(file.getOriginalFilename())) {
            log.info("This file {} has been already existed: ", file.getOriginalFilename());
            return tbl_uploadfile.builder().fileName(file.getOriginalFilename()).build();
        }

        var uploadFile = tbl_uploadfile.builder().fileName(file.getOriginalFilename()).fileType(file.getContentType())
                .grpData(file.getBytes()).build();

        return uploadFileRepository.save(uploadFile);
    }

    @Override
    public tbl_uploadfile getFileUpload(String fileName) {
        return uploadFileRepository.findByFileName(fileName).orElseThrow(FileNotFoundException::new);
    }
}
