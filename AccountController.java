package com.bank.api.controller;

import com.bank.api.dto.CreateAccountDTO;
import com.bank.api.dto.TransactionDTO;
import com.bank.api.dto.TransferRequestDTO;
import com.bank.api.model.Account;
import com.bank.api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountService getAccountService() {
        return accountService;
    }

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountDTO dto) {
        Account account = accountService.createAccount(dto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccountByNumber(accountNumber));
    }

    @PostMapping("/transactions/deposit")
    public ResponseEntity<Account> deposit(@Valid @RequestBody TransactionDTO dto) {
        return ResponseEntity.ok(accountService.deposit(dto.getAccountNumber(), dto.getAmount()));
    }

    @PostMapping("/transactions/withdraw")
    public ResponseEntity<Account> withdraw(@Valid @RequestBody TransactionDTO dto) {
        return ResponseEntity.ok(accountService.withdraw(dto.getAccountNumber(), dto.getAmount()));
    }

    @PostMapping("/transactions/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequestDTO dto) {
        accountService.transfer(dto);
        return ResponseEntity.ok("Transfer completed successfully.");
    }
}
