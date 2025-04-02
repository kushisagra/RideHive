package com.myUber.myUber.service.impl;


import com.myUber.myUber.DTO.WalletTransactionDTO;
import com.myUber.myUber.Entities.WalletTransaction;
import com.myUber.myUber.Repositories.WalletTransactionRepo;
import com.myUber.myUber.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionImp implements WalletTransactionService {
   private final WalletTransactionRepo walletTransactionRepo;
private final ModelMapper modelMapper;
   @Override
    public void createNewWAalletTransaction(WalletTransaction walletTransaction) {


       walletTransactionRepo.save(walletTransaction);
    }
}
