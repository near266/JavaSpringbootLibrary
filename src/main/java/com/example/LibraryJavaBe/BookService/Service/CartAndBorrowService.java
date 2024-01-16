package com.example.LibraryJavaBe.BookService.Service;

import com.example.LibraryJavaBe.BookService.Entities.*;
import com.example.LibraryJavaBe.BookService.InterfaceSvc.IBorrowerCardSvc;
import com.example.LibraryJavaBe.BookService.Repository.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartAndBorrowService implements IBorrowerCardSvc {
    private final Book_BorrowerCardRepo book_borrowerCardRepo;
    private  final BorrowerCardRepo borrowerCardRepo;
    private final DeliveryInfoRepo deliveryInfoRepo;
    private final BorrowerSlipRepo borrowerSlipRepo;
    private final BorrowerSlip_BookRepo borrowerSlip_bookRepo;


    @Override
    public Book_BorrowerCard AddCardReadBook(Book_BorrowerCard borrowerCard) {

       var res= book_borrowerCardRepo.save(borrowerCard);
        return res;
    }

    @Override
    public BorrowerCard AddBorrowerCard(BorrowerCard borrowerCard) {
        return borrowerCardRepo.save(borrowerCard);
    }

    @Override
    public List<Book_BorrowerCard> GetAllCardUserId(Integer UserId) {
        var result = new ArrayList<Book_BorrowerCard>();
        var getallB_B =book_borrowerCardRepo.findAll();
        var getall = borrowerCardRepo.findAll();
        var listCardBor=new ArrayList<BorrowerCard>();
        for(BorrowerCard c : getall){
            if(c.getUserId().equals(UserId)){
                listCardBor.add(c);
            }

        }
       for (Book_BorrowerCard bb : getallB_B){
           if(listCardBor.contains(bb.getBorrowerCard())){
               result.add(bb);
           }
       }
        return result;
    }

    @Override
    public Boolean DeleteBook_BorrwoCard(Integer id) {
        book_borrowerCardRepo.deleteById(id);
        return true;
    }

    @Override
    public DeliveryInfo AddDelivery(DeliveryInfo deliveryInfo) {
        var res =deliveryInfoRepo.save(deliveryInfo);
;        return res;
    }

    @Override
    public BorrowerSlip addBorrowerSlip(BorrowerSlip borrowerSlip) {
        return borrowerSlipRepo.save(borrowerSlip);
    }

    @Override
    public BorrowerSlip_Book addBorrowerSlip_Book(BorrowerSlip_Book borrowerSlip_book) {
        return borrowerSlip_bookRepo.save(borrowerSlip_book);
    }

}
