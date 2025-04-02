package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Enums.PaymentStatus;
import com.myUber.myUber.Entities.Enums.TransactionMEthod;
import com.myUber.myUber.Entities.Payment;
import com.myUber.myUber.Entities.Wallet;
import com.myUber.myUber.Repositories.PaymentRepo;
import com.myUber.myUber.Strategies.PaymentStrategy;
import com.myUber.myUber.service.PaymentService;
import com.myUber.myUber.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//comisson deducting from wallet driver
@Service
@RequiredArgsConstructor
public class CODpaymentStrategy implements PaymentStrategy {
   private final WalletService walletService;

   private final PaymentRepo paymentRepo;
    @Override
    public void processPayment(Payment payment) {

        Driver driver=payment.getRide().getDriver();

double platformComisson= payment.getAmount()*PLATFORM_COMISSION;
walletService.deducutMoneyFromwallet(driver.getUser(),platformComisson,null,payment.getRide(), TransactionMEthod.Ride);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepo.save(payment);


    }
}
