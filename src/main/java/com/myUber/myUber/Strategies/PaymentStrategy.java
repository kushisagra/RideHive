package com.myUber.myUber.Strategies;

import com.myUber.myUber.Entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMISSION=0.3;

    void processPayment(Payment payment);
}
