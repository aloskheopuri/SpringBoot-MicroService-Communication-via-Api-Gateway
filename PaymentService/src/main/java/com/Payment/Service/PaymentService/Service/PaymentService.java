package com.Payment.Service.PaymentService.Service;

import com.Payment.Service.PaymentService.Entities.PaymentInfoEntities;
import com.Payment.Service.PaymentService.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;

    public ResponseEntity<PaymentInfoEntities> createPayment(PaymentInfoEntities paymentInfoEntities){
        PaymentInfoEntities paymentInfoEntities1=paymentRepository.save(paymentInfoEntities);
        return new ResponseEntity<>(paymentInfoEntities1, HttpStatus.CREATED);
    }

    public Optional<PaymentInfoEntities> findPaymentById(int Paymentid){
        Optional<PaymentInfoEntities> paymentInfoEntities=paymentRepository.findById(Paymentid);
        return paymentInfoEntities;
    }

    public List<PaymentInfoEntities> findallPayment(){
        List<PaymentInfoEntities> paymentInfoEntities=paymentRepository.findAll();
        return paymentInfoEntities;
    }

}
