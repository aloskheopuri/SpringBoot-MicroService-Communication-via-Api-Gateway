package com.Booking.Service.BookingService.Service;

import com.Booking.Service.BookingService.DTO.PaymentDto;
import com.Booking.Service.BookingService.DTO.TransactionIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class TransactionService {
    @Autowired
    public RestTemplate restTemplate;

    public TransactionService(){}

    public int getTransactionId(PaymentDto paymentDto){
        String paymnetUrl="http://localhost:8082/transaction";
        TransactionIdDto transactionIdDto=restTemplate.postForObject(paymnetUrl,paymentDto,TransactionIdDto.class,new Object());
        if (transactionIdDto == null) {
            return 0;
        } else {
            return transactionIdDto.getTransactionId();
        }

    }
}
