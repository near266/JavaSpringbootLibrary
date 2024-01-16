package com.example.LibraryJavaBe.BookService.Entities;
;
import com.example.LibraryJavaBe.UserService.Entites.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "BorrowerSlip")
public class BorrowerSlip extends BaseEntity {



        private Integer UserId;

        @Column(name = "dueDate")
        private LocalDate dueDate;

        @Column(name = "returnDate")
        private LocalDate returnDate;
        private Integer Penalty;
        private Integer stateId;
        private Integer typeId;
        @ManyToOne
        @JoinColumn(name = "deliveryId" )
        private DeliveryInfo deliveryInfo;
         @OneToMany(mappedBy = "borrowerSlip")
        private Set<BorrowerSlip_Book> borrowerSlip_books;


}
