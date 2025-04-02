package com.myUber.myUber.Repositories;

import com.myUber.myUber.Entities.Payment;
import com.myUber.myUber.Entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Long> {
    Optional <Payment>findByRide(Ride ride);
}
