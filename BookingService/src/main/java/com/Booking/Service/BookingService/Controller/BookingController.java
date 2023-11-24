package com.Booking.Service.BookingService.Controller;

import com.Booking.Service.BookingService.DTO.PaymentDto;
import com.Booking.Service.BookingService.Entities.BookingInfoEntity;
import com.Booking.Service.BookingService.Exception.PaymentException;
import com.Booking.Service.BookingService.Service.BookingService;
import com.Booking.Service.BookingService.Service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/hotel")
@RestController
public class BookingController {

    @Autowired
    public BookingService bookingService;
    @Autowired
    public TransactionService transactionService;

    @Autowired
    public ModelMapper modelMapper;

    @PostMapping(value = "/booking", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<BookingInfoEntity> createNewBooking(@RequestBody BookingInfoEntity bookingInfoEntity){
        BookingInfoEntity createdBooking=bookingService.createBooking(bookingInfoEntity);
        return new  ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping("/booking/{bookingid}")
    public ResponseEntity  getBooking(@PathVariable int bookingid){
        BookingInfoEntity savedbooking=bookingService.getBookingbyid(bookingid);

        return new ResponseEntity(savedbooking, HttpStatus.OK);
    }

    @GetMapping("/bookings")
    public List<BookingInfoEntity> getAllBookings(){
        List<BookingInfoEntity> bookingInfoEntities=bookingService.getAllBooking();
        return bookingInfoEntities;
    }


    @PostMapping("/booking/{bookingId}/transaction")
    public ResponseEntity CreateTransaction(@RequestBody PaymentDto paymentDto, @PathVariable int bookingId) {

        if (!paymentDto.getPaymentMode().equalsIgnoreCase("card") && !paymentDto.getPaymentMode().equalsIgnoreCase("upi")) {
            throw new PaymentException(400, "Invalid Mode Of Payment");
        } else {
            BookingInfoEntity bookingInfo = bookingService.getBookingbyid(bookingId);
            if (bookingInfo == null) {
                throw new PaymentException(400, " Invalid Booking Id ");
            } else {
                int transactionId = transactionService.getTransactionId(paymentDto);
                bookingInfo.setTransactionId(transactionId);
                BookingInfoEntity UpdateBooking = bookingService.createBooking(bookingInfo);
                String message = "Booking Confirmed With AddharNo" + bookingInfo.getAddharNumber();
                BookingInfoEntity bookingInfoEntity = modelMapper.map(UpdateBooking, bookingInfo.getClass());
                return new ResponseEntity(bookingInfoEntity, HttpStatus.OK);
            }
        }


    }}
