package com.Payment.Service.PaymentService.Repository;

import com.Payment.Service.PaymentService.Entities.PaymentInfoEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfoEntities,Integer> {
}
