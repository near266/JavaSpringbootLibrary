package com.example.LibraryJavaBe.Request.CardRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowCardRequest {


    public Long BookId;

    public Integer quantity;

}
