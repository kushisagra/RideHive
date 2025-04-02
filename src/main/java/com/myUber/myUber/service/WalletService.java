package com.myUber.myUber.service;

import com.myUber.myUber.Entities.Enums.TransactionMEthod;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Entities.Wallet;
import org.springframework.stereotype.Service;


public interface WalletService {

Wallet deducutMoneyFromwallet(User user, Double amount, String transactionId, Ride ride, TransactionMEthod transactionMEthod);
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMEthod transactionMEthod);

    void withdrawAllMuMoneyFromWallet();
    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);
    Wallet findByUser(User user);

}
