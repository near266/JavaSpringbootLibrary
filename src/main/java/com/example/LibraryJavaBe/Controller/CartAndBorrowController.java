package com.example.LibraryJavaBe.Controller;

import com.example.LibraryJavaBe.BookService.Entities.*;
import com.example.LibraryJavaBe.BookService.Service.BookService;
import com.example.LibraryJavaBe.BookService.Service.CartAndBorrowService;
import com.example.LibraryJavaBe.Request.CardRequest.BorrowCardRequest;
import com.example.LibraryJavaBe.Response.CardRes;
import com.example.LibraryJavaBe.Response.ResgisterBorrowForm;
import com.example.LibraryJavaBe.authService.AuthenticationService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Builder
@Slf4j
@RequestMapping("/api/v1/Cart")
public class CartAndBorrowController {
private final CartAndBorrowService _cartAndBorrowService;
private final BookService _bookService;
    private  final AuthenticationService service;

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Current User: " + authentication.getName();


    }

    private Integer getCurrentUserID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return service.getUserId(authentication.getName());
    }

    @PostMapping("/ResgisterBorrowForm")
    public ResponseEntity<Boolean> ResgisterBorrowForm(@RequestBody ResgisterBorrowForm rq){
        var deliRq= DeliveryInfo.builder()
                .receiverName(rq.reciveName)
                .address(rq.getAdress())
                .phoneNumber(rq.getPhoneNumber())
                .build();
        var deliRes= _cartAndBorrowService.AddDelivery(deliRq);
        var BorroweSliprq = BorrowerSlip.builder()
                .UserId(getCurrentUserID())
                .typeId(0)
                .stateId(0)
                .Penalty(0)
                .deliveryInfo(deliRes)
                .build();
        var BorroweSlipRes = _cartAndBorrowService.addBorrowerSlip(BorroweSliprq);
        for (BorrowCardRequest b : rq.getAddBookrq()){
            var rqform= BorrowerSlip_Book.builder()
                    .borrowerSlip(BorroweSlipRes)
                    .book(_bookService.getBookById(b.BookId))
                    .quantity(b.getQuantity())
                    .build();
            _cartAndBorrowService.addBorrowerSlip_Book(rqform);
        }

        return  ResponseEntity.ok(true);
    }
    @DeleteMapping("DeleteBook_BorrwoCard")
    public ResponseEntity<Boolean> DeleteBook_BorrwoCard(@RequestParam Integer id){
        var res =_cartAndBorrowService.DeleteBook_BorrwoCard(id);
        return  ResponseEntity.ok(res);
    }
    @GetMapping("GetAllCardByUserId")
    public ResponseEntity<CardRes> GetAllCardByUserId(){
        var res = _cartAndBorrowService.GetAllCardUserId(getCurrentUserID());
        Integer tl =0;
        for(Book_BorrowerCard bb : res){
            tl+= bb.getQuantity();
        }
        var result = CardRes.builder()
                .bookBorrowerCards(res)
                .total(tl)

                .build();


        return  ResponseEntity.ok(result);
    }
    @GetMapping("test")
    public ResponseEntity<String> sayHello(){
        return  ResponseEntity.ok("Hello");
    }

    @PostMapping("/AddCardBorrwer")
    public ResponseEntity<Integer> AddCardBorrwer( @RequestParam("bookid") Long BookId,@RequestParam("quantity") Integer quantity ){
        var userid = getCurrentUserID();
        BorrowerCard card = BorrowerCard.builder()
                .UserId(userid)
                .build();
      var cardSave=  _cartAndBorrowService.AddBorrowerCard(card);
      var book = _bookService.getBookById(BookId);
      var CardBorrowrq= Book_BorrowerCard.builder()
              .quantity(quantity)
              .book(book)
              .borrowerCard(cardSave)
              .build();
var CardBorrowrqSave= _cartAndBorrowService.AddCardReadBook(CardBorrowrq);
 return ResponseEntity.ok(1);
    }

}
