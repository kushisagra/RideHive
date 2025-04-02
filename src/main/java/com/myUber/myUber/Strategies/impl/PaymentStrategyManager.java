package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.Enums.PaymentMethod;
import com.myUber.myUber.Strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {


    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CODpaymentStrategy coDpaymentStrategy;




    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> coDpaymentStrategy;

    };

}
    }
