package com.bookmybus.busbooking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmybus.busbooking.entity.Payment;
import com.bookmybus.busbooking.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Payment createPayment(Payment payment) {
		// TODO Auto-generated method stub
		payment.setTransactionDate(LocalDateTime.now()); // Set transaction date to the current date and time
        return paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentById(Long paymentId) {
		// TODO Auto-generated method stub
		Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
	    if (paymentOptional.isPresent()) {
	        return paymentOptional.get();
	    } else {
	        throw new RuntimeException("Payment not found with id " + paymentId);
	    }
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}

	@Override
	public Payment updatePaymentStatus(Long paymentId, String status) {
		// TODO Auto-generated method stub
		Payment existingPayment = getPaymentById(paymentId);
        existingPayment.setStatus(status);
        return paymentRepository.save(existingPayment);
	}

}
