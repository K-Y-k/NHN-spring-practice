package com.example.demo.shell;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.price.dto.Price;
import com.example.demo.price.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("eng")
public class MyCommandsTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private MyCommands myCommands;


    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("MyCommands login() Test")
    void myCommandsLoginTest() {
        // given
        Account account1 = new Account(1L, "1", "선도형");
        when(authenticationService.login(1L, "1")).thenReturn(account1);

        // when
        String result = myCommands.login(1L, "1");

        // then
        assertEquals("Account(id=1, password=1, name=선도형)", result);

    }

    @Test
    @DisplayName("MyCommands logout() Test")
    void myCommandsLogoutTest() {
        // given
        Account account = new Account(1L, "1", "선도형");
        when(authenticationService.login(1L, "1")).thenReturn(account);

        // when
        myCommands.login(1L, "1");
        myCommands.logout();

        // then
        assertThrows(NullPointerException.class, () -> myCommands.currentUser());
    }

    @Test
    @DisplayName("MyCommands currentUser() Test")
    void myCommandsCurrentUserTest() {
        // given
        Account account = new Account(1L, "1", "선도형");
        when(authenticationService.login(1L, "1")).thenReturn(account);
        when(authenticationService.getCurrentAccount()).thenReturn(account);

        // when
        myCommands.login(1L, "1");

        // then
        assertEquals("Account(id=1, password=1, name=선도형)", myCommands.currentUser());
    }

    @Test
    @DisplayName("MyCommands city() Test")
    void myCommandsCityMethodTest() {
        // given
        List<String> cities = new ArrayList<>();
        cities.add("동두천시");
        cities.add("파주시");
        when(priceService.cities()).thenReturn(cities);

        Account account = new Account(1L, "1", "선도형");
        when(authenticationService.getCurrentAccount()).thenReturn(account);

        // when
        String result = myCommands.city();

        // then
        assertEquals("[동두천시, 파주시]", result);
    }

    @Test
    @DisplayName("MyCommands sector() Test")
    void myCommandsSectorMethodTest() {
        // given
        List<String> sectorsList = new ArrayList<>();
        sectorsList.add("주방용");
        sectorsList.add("일반용");

        when(priceService.sectors("동두천시")).thenReturn(sectorsList);

        // when
        String result = myCommands.sector("동두천시");

        // then
        assertEquals("[주방용, 일반용]", result);

    }

    @Test
    @DisplayName("MyCommands price() Test")
    void myCommandsPriceMethodTest() {
        // given
        Price price = new Price(1L, "동두천시", "주방용", 690);
        when(priceService.price("동두천시", "주방용")).thenReturn(price);

        // when
        String result = myCommands.price("동두천시", "주방용");

        // then
        assertEquals("Price(id=1, city=동두천시, sector=주방용, unitPrice=690)", result);
    }

    @Test
    @DisplayName("MyCommands billTotal() Test")
    void myCommandsBillTotalMethodTest() {
        // given
        Price mockPrice = new Price(1L, "동두천시", "주방용", 690);
        when(priceService.billTotal("동두천시", "주방용",  10)).thenReturn("city: 동두천시, sector: 주방용, unit price(won): 690, bill total(won): 6900");

        // when
        String result = myCommands.billTotal("동두천시", "주방용", 10);

        // then
        assertEquals("city: 동두천시, sector: 주방용, unit price(won): 690, bill total(won): 6900", result);
    }

}