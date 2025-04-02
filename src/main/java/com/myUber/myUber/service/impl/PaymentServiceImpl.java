package com.myUber.myUber.service.impl;

import com.myUber.myUber.Entities.Enums.PaymentStatus;
import com.myUber.myUber.Entities.Payment;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Repositories.PaymentRepo;
import com.myUber.myUber.Strategies.impl.PaymentStrategyManager;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
private  final PaymentRepo paymentRepo;
private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment=paymentRepo.findByRide(ride)
                        .orElseThrow(()->new ResourceNotFoundException("payment not found dor ride with id "+ride));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createPAyment(Ride ride) {

        Payment payment=Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepo.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {

        payment.setPaymentStatus(status);
        paymentRepo.save(payment);

    }
}
