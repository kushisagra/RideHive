package com.myUber.myUber.service.impl;

import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.WalletDTO;
import com.myUber.myUber.DTO.WalletTransactionDTO;
import com.myUber.myUber.Entities.Enums.TransactionMEthod;
import com.myUber.myUber.Entities.Enums.TransactionType;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Entities.Wallet;
import com.myUber.myUber.Entities.WalletTransaction;
import com.myUber.myUber.Repositories.WalletRepo;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.WalletService;
import com.myUber.myUber.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
   private final WalletRepo walletRepo;
   private final ModelMapper modelMapper;
   private final WalletTransactionService walletTransactionService;

    @Override
    public Wallet deducutMoneyFromwallet(User user, Double amount, String transactionId, Ride ride, TransactionMEthod transactionMEthod) {
        Wallet wallet=findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);
        return walletRepo.save(wallet);
    }

    @Override
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMEthod transactionMEthod) {
       Wallet wallet=findByUser(user);

        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction= WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMEthod(transactionMEthod)
                .amount(amount)
                .build();

      //  walletTransactionService.createNewWAalletTransaction(walletTransaction);
wallet.getTransactions().add(walletTransaction);
        return walletRepo.save(wallet);
    }

    @Override
    public void withdrawAllMuMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepo.findById(walletId)
                .orElseThrow(()->new ResourceNotFoundException("wallet not found with id "+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
       Wallet wallet=new Wallet();
       wallet.setUser(user);
       return walletRepo.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepo.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("wallet not found for user"+user));

    }
}
