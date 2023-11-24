package com.Booking.Service.BookingService.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private int bookingId;
    private String upiId;
    private String paymentMode;
    private String cardNumber;
}
