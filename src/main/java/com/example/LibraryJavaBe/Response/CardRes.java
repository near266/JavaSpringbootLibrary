package com.example.LibraryJavaBe.Response;

import com.example.LibraryJavaBe.BookService.Entities.Book_BorrowerCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardRes {
    public List<Book_BorrowerCard> bookBorrowerCards;
    public Integer total;
}
