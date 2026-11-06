package com.bank.api;

import com.bank.api.dto.CreateAccountDTO;
import com.bank.api.dto.TransferRequestDTO;
import com.bank.api.exception.InsufficientFundsException;
import com.bank.api.model.Account;
import com.bank.api.repository.AccountRepository;
import com.bank.api.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account acc1;
    private Account acc2;

    @BeforeEach
    void setUp() {
        acc1 = new Account("ACC001", "Rendani Mmola", "rendania460@gmail.com", BigDecimal.valueOf(1000.00));
        acc2 = new Account("ACC002", "Jane Doe", "jane@example.com", BigDecimal.valueOf(500.00));
    }

    @Test
    void testCreateAccount() {
        CreateAccountDTO dto = new CreateAccountDTO();
        dto.setAccountHolderName("Rendani Mmola");
        dto.setEmail("rendania460@gmail.com");
        dto.setInitialDeposit(BigDecimal.valueOf(1000.00));

        when(accountRepository.save(any(Account.class))).thenReturn(acc1);

        Account created = accountService.createAccount(dto);
        assertNotNull(created);
        assertEquals("ACC001", created.getAccountNumber());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testTransfer_Successful() {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccountNumber("ACC001");
        request.setDestinationAccountNumber("ACC002");
        request.setAmount(BigDecimal.valueOf(300.00));

        when(accountRepository.findByAccountNumber("ACC001")).thenReturn(Optional.of(acc1));
        when(accountRepository.findByAccountNumber("ACC002")).thenReturn(Optional.of(acc2));

        accountService.transfer(request);

        assertEquals(BigDecimal.valueOf(700.00), acc1.getBalance());
        assertEquals(BigDecimal.valueOf(800.00), acc2.getBalance());
        verify(accountRepository, times(1)).save(acc1);
        verify(accountRepository, times(1)).save(acc2);
    }

    @Test
    void testTransfer_InsufficientFunds() {
        TransferRequestDTO request = new TransferRequestDTO();
        request.setSourceAccountNumber("ACC001");
        request.setDestinationAccountNumber("ACC002");
        request.setAmount(BigDecimal.valueOf(1500.00));

        when(accountRepository.findByAccountNumber("ACC001")).thenReturn(Optional.of(acc1));
        when(accountRepository.findByAccountNumber("ACC002")).thenReturn(Optional.of(acc2));

        assertThrows(InsufficientFundsException.class, () -> accountService.transfer(request));
        verify(accountRepository, never()).save(any(Account.class));
    }
}
