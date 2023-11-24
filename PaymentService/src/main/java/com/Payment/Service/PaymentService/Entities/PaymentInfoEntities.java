package com.Payment.Service.PaymentService.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class PaymentInfoEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "paymentMode")
    private String paymentMode;

    @Column(name = "bookingId",nullable = false)
    private int bookingId;

    @Column(name = "upiId",nullable = true)
    private String upiId;

    @Column(name = "cardNumber",nullable = true)
    private String cardNumber;
}
