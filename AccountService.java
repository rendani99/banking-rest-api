package com.bank.api.service;

import com.bank.api.dto.CreateAccountDTO;
import com.bank.api.dto.TransferRequestDTO;
import com.bank.api.exception.InsufficientFundsException;
import com.bank.api.exception.ResourceNotFoundException;
import com.bank.api.model.Account;
import com.bank.api.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account createAccount(CreateAccountDTO dto) {
        String accountNumber = "ACC" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Account account = new Account(accountNumber, dto.getAccountHolderName(), dto.getEmail(), dto.getInitialDeposit());
        return accountRepository.save(account);
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with number: " + accountNumber));
    }

    @Transactional
    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance for withdrawal. Current balance: " + account.getBalance());
        }
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }

    @Transactional
    public void transfer(TransferRequestDTO request) {
        Account source = getAccountByNumber(request.getSourceAccountNumber());
        Account destination = getAccountByNumber(request.getDestinationAccountNumber());

        if (source.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientFundsException("Insufficient funds in source account for transfer.");
        }

        source.setBalance(source.getBalance().subtract(request.getAmount()));
        destination.setBalance(destination.getBalance().add(request.getAmount()));

        accountRepository.save(source);
        accountRepository.save(destination);
    }
}
