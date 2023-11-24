package com.Booking.Service.BookingService.Service;


import com.Booking.Service.BookingService.Entities.BookingInfoEntity;
import com.Booking.Service.BookingService.Exception.PaymentException;
import com.Booking.Service.BookingService.Repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

    @Autowired
    public BookingRepo bookingRepo;

    private List<String> RandomRoomNumbers(int count) {
        Random random = new Random();
        int Limit = 100;

        List<String> listOfRooms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            listOfRooms.add(String.valueOf(random.nextInt(Limit)));
        }
        return listOfRooms;
    }

    public BookingInfoEntity createBooking(BookingInfoEntity bookingInfoEntity) {
        // getting room numbers
        bookingInfoEntity.setRoomNumbers(String.join(",", RandomRoomNumbers(bookingInfoEntity.getNumOfRooms())));

        // calculating room price by assuming 1000INR per room
        Long numberOfDays = (bookingInfoEntity.getToDate().getTime() - bookingInfoEntity.getFromDate().getTime()) / (24 * 60 * 60 * 1000);
        bookingInfoEntity.setRoomPrice((int) (1000 * bookingInfoEntity.getNumOfRooms() * numberOfDays));

        //setting booked on
        bookingInfoEntity.setBookedOn(new Date());

        return bookingRepo.save(bookingInfoEntity);

    }


    public BookingInfoEntity getBookingbyid(int bookingId) {
       Optional<BookingInfoEntity> bookingPresent = bookingRepo.findById(bookingId);
        if (!bookingPresent.isPresent()) {
            throw new PaymentException(400,"InvalidBookingId");
        } else {
            return bookingPresent.get();
        }
    }

    public List<BookingInfoEntity> getAllBooking(){
        return bookingRepo.findAll();
    }




}
