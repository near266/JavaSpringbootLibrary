package com.example.LibraryJavaBe.BookService.InterfaceSvc;

import com.example.LibraryJavaBe.BookService.Entities.*;

import java.util.List;

public interface IBorrowerCardSvc {
    Book_BorrowerCard AddCardReadBook(Book_BorrowerCard borrowerCard);
    BorrowerCard AddBorrowerCard(BorrowerCard borrowerCard);

    List<Book_BorrowerCard> GetAllCardUserId(Integer UserId);

    Boolean DeleteBook_BorrwoCard(Integer id);

    DeliveryInfo AddDelivery(DeliveryInfo deliveryInfo);
    BorrowerSlip addBorrowerSlip(BorrowerSlip borrowerSlip);
    BorrowerSlip_Book  addBorrowerSlip_Book(BorrowerSlip_Book borrowerSlip_book);

}
