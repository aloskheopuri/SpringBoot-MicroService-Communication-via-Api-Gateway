package com.Payment.Service.PaymentService.Controller;

import com.Payment.Service.PaymentService.Entities.PaymentInfoEntities;
import com.Payment.Service.PaymentService.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PaymentController {
    @Autowired
    public PaymentService paymentService;
@PostMapping("/transaction")
    public ResponseEntity<PaymentInfoEntities> createPayment(@RequestBody PaymentInfoEntities paymentInfoEntities){
        PaymentInfoEntities paymentInfoEntities1= paymentService.createPayment(paymentInfoEntities).getBody();
        return new ResponseEntity<>(paymentInfoEntities1, HttpStatus.CREATED);
    }
@GetMapping("/transaction/{Paymnetid}")
    public Optional<PaymentInfoEntities> findPaymentById(@PathVariable int Paymentid){
        Optional<PaymentInfoEntities> paymentInfoEntities=paymentService.findPaymentById(Paymentid);
        return paymentInfoEntities;
    }
@GetMapping("/transactions")
    public List<PaymentInfoEntities> findallPayment(){
        List<PaymentInfoEntities> paymentInfoEntities=paymentService.findallPayment();
        return paymentInfoEntities;
    }
}
