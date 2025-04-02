package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Enums.PaymentStatus;
import com.myUber.myUber.Entities.Enums.TransactionMEthod;
import com.myUber.myUber.Entities.Payment;
import com.myUber.myUber.Entities.Rider;
import com.myUber.myUber.Entities.Wallet;
import com.myUber.myUber.Repositories.PaymentRepo;
import com.myUber.myUber.Strategies.PaymentStrategy;
import com.myUber.myUber.service.PaymentService;
import com.myUber.myUber.service.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


//rider has 230 , ride cot 100, rider=130, driver
@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepo paymentRepo;
    @Override
    @Transactional
    public void processPayment(Payment payment) {

Driver driver=payment.getRide().getDriver();
Rider rider=payment.getRide().getRider();

walletService.deducutMoneyFromwallet(rider.getUser(),
        payment.getAmount(),null,payment.getRide(), TransactionMEthod.Ride);
double driversCut= payment.getAmount()*(1-PLATFORM_COMISSION);
walletService.addMoneyToWallet(driver.getUser(),driversCut,null,payment.getRide(),TransactionMEthod.Ride);
payment.setPaymentStatus(PaymentStatus.CONFIRMED);
paymentRepo.save(payment);
    }
}
