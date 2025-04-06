package com.example.demo.account.service;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private Account currentAccount;
    private final DataParser dataParser;


    public Account login(Long id, String password) {
        if (Objects.nonNull(currentAccount)) {
            throw new IllegalArgumentException("현재 로그인 되어 있습니다. 로그아웃 먼저 해주세요");
        }

        List<Account> accountList = dataParser.accounts();

        for (Account account : accountList) {
            if (account.getId() == id && account.getPassword().equals(password)) {
                currentAccount = account;
                break;
            }
        }

        if (Objects.isNull(currentAccount)) {
            return null;
        }

        return currentAccount;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void logout() {
        currentAccount = null;
    }
}
