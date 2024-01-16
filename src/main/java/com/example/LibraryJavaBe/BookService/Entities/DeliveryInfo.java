package com.example.LibraryJavaBe.BookService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@ToString
@Table(name = "DeliveryInfo")
public class DeliveryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer deliveryId;
    private String receiverName;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "deliveryInfo")
    @JsonIgnore
    private Set<BorrowerSlip> borrowerSlips;
}
