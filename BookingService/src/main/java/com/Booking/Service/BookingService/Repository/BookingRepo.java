package com.Booking.Service.BookingService.Repository;

import com.Booking.Service.BookingService.Entities.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<BookingInfoEntity,Integer> {
}
