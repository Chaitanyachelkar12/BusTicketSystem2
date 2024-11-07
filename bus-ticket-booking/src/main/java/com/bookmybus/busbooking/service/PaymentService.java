package com.bookmybus.busbooking.service;

import java.util.List;

import com.bookmybus.busbooking.entity.Payment;

public interface PaymentService {

	Payment createPayment(Payment payment);
    Payment getPaymentById(Long paymentId);
    List<Payment> getAllPayments();
    Payment updatePaymentStatus(Long paymentId, String status);
}
