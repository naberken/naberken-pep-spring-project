package com.example.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){

        if (account.getPassword().length() <= 4 || account.getUsername().length() == 0){
            return null;
        } else if(accountRepository.findAccountByUsername(account.getUsername()) != null){
            return new Account(null, "DUPLICATE", null);
        }

        return accountRepository.save(account);
    }

    public Account loginAccount(Account account){
        return accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
    }

}
