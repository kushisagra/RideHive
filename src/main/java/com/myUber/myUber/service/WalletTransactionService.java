package com.myUber.myUber.service;

import com.myUber.myUber.DTO.WalletTransactionDTO;
import com.myUber.myUber.Entities.WalletTransaction;
import org.springframework.stereotype.Service;


public interface WalletTransactionService {


    void createNewWAalletTransaction(WalletTransaction walletTransaction);
}
