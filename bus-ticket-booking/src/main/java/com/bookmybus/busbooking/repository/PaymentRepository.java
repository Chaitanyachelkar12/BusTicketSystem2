package com.bookmybus.busbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmybus.busbooking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
