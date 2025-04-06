package com.example.demo.shell;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.price.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Objects;

@ShellComponent
@RequiredArgsConstructor
public class MyCommands {

    private final AuthenticationService authenticationService;
    private final PriceService priceService;

    @ShellMethod
    public String login(long id, String password) {
        Account loginAccount = authenticationService.login(id, password);
        if (Objects.isNull(loginAccount)) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        return loginAccount.toString();
    }

    @ShellMethod
    public String logout() {
        authenticationService.logout();
        return "good bye";
    }

    @ShellMethod
    public String currentUser() {
        return authenticationService.getCurrentAccount().toString();
    }

    @ShellMethod
    public String city() {
        if (Objects.isNull(authenticationService.getCurrentAccount())) {
            throw new IllegalArgumentException("로그인 먼저 해주세요");
        }
        
        return priceService.cities().toString();
    }

    @ShellMethod
    public String sector(String city) {
        return priceService.sectors(city).toString();
    }

    @ShellMethod
    public String price(String city, String sector) {
        return priceService.price(city, sector).toString();
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        return priceService.billTotal(city, sector, usage);
    }
}