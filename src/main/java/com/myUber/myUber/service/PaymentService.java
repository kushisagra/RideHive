package com.myUber.myUber.service;

import com.myUber.myUber.Entities.Enums.PaymentStatus;
import com.myUber.myUber.Entities.Payment;
import com.myUber.myUber.Entities.Ride;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createPAyment(Ride ride);
    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
