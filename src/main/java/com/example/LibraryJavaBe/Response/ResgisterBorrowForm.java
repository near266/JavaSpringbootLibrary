package com.example.LibraryJavaBe.Response;

import com.example.LibraryJavaBe.Request.CardRequest.BorrowCardRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResgisterBorrowForm {
    public String reciveName;
    public String phoneNumber;
    public String adress;
    public List<BorrowCardRequest> addBookrq;
 }
