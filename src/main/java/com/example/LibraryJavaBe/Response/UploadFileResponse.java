package com.example.LibraryJavaBe.Response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadFileResponse {

        private boolean isError;
        private String fileName;
        private String fileLink;

}
