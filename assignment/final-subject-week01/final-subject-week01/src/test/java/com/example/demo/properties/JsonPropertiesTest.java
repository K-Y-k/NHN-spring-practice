package com.example.demo.properties;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.impl.JsonDataParser;
import com.example.demo.price.dto.Price;
import com.example.demo.price.formatter.EnglishOutputFormatter;
import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.service.PriceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=json",
        "file.price-path=price.json",
        "file.account-path=account.json"
})
public class JsonPropertiesTest {

    /**
     * Spy로 테스트 코드
     */

    @Autowired
    DataParser dataParser;

    @SpyBean
    private AuthenticationService authenticationService;

    @SpyBean
    private PriceService priceService;


    @AfterEach
    void afterEach() {
        if (Objects.nonNull(authenticationService.getCurrentAccount())) {
            authenticationService.logout();
        }
    }

    @Test
    void beanTest() {
        assertInstanceOf(JsonDataParser.class, dataParser);
    }

    /**
     * AuthenticationService는 실제 객체로 사용
     */
    @Test
    void loginTest() throws Exception {
        // when
        authenticationService.login(1L, "1");

        // then
        Account account = authenticationService.getCurrentAccount();
        assertEquals("선도형", account.getName());
    }

    @Test
    void logoutTest() throws Exception {
        // given
        authenticationService.login(1L, "1");

        // when
        authenticationService.logout();

        // then
        Account account = authenticationService.getCurrentAccount();
        assertNull(account);
    }

    
    /**
     * PrcieService는 가짜 객체로 사용
     */
    @Test
    void cityTest() throws Exception {
        // given
        List<String> mockCities = List.of("동두천시", "서울시", "경기도", "부산시");
        when(priceService.cities()).thenReturn(mockCities);

        // when
        List<String> cities = priceService.cities();

        // then
        assertEquals(4, cities.size());
        assertTrue(cities.contains("동두천시"));
        verify(priceService, times(1)).cities();
    }

    @Test
    void sectorsTest() throws Exception {
        // given
        List<String> mockSectors = List.of("가정용", "일반용", "대중목욕탕용");
        when(priceService.sectors("동두천시")).thenReturn(mockSectors);

        // when
        List<String> sectors = priceService.sectors("동두천시");

        // then
        assertEquals(3, sectors.size());
        assertTrue(sectors.contains("가정용"));
        verify(priceService, times(1)).sectors("동두천시");
    }

    @Test
    void priceTest() throws Exception {
        // given
        Price mockPrice = new Price(1L, "동두천시", "가정용", 690);
        when(priceService.price("동두천시", "가정용")).thenReturn(mockPrice);

        // when
        Price price = priceService.price("동두천시", "가정용");

        // then
        assertEquals("동두천시", price.getCity());
        assertEquals("가정용", price.getSector());
        assertEquals(690, price.getUnitPrice());
        verify(priceService, times(1)).price("동두천시", "가정용");
    }

    @Test
    void billTotalTest() throws Exception {
        // given
        String mockBillTotal = "6900";
        when(priceService.billTotal("동두천시", "가정용", 10)).thenReturn(mockBillTotal);

        // when
        String billTotal = priceService.billTotal("동두천시", "가정용", 10);

        // then
        assertEquals("6900", billTotal);
        verify(priceService, times(1)).billTotal("동두천시", "가정용", 10);
    }


    @Test
    void OutPutFormatterTest() throws Exception {
        // given
        Price price = priceService.price("동두천시", "가정용");

        KoreanOutputFormatter koreanOutputFormatter = new KoreanOutputFormatter();
        EnglishOutputFormatter englishOutputFormatter = new EnglishOutputFormatter();

        // when
        String korean = koreanOutputFormatter.format(price, 10);
        String english = englishOutputFormatter.format(price, 10);

        // then
        assertTrue(korean.contains("690"));
        assertTrue(english.contains("690"));
        assertNotEquals(korean, english);
    }
}
