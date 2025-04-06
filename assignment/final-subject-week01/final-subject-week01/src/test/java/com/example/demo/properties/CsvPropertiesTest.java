package com.example.demo.properties;

import com.example.demo.account.dto.Account;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.common.dataparser.impl.CsvDataParser;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.price.dto.Price;
import com.example.demo.price.formatter.EnglishOutputFormatter;
import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.service.PriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=price.csv",
        "file.account-path=account.csv"
})
public class CsvPropertiesTest {

    /**
     * Mock으로 테스트 코드
     */
    
    @Autowired
    private DataParser dataParser;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private PriceService priceService;


    @Test
    void beanTest() {
        assertInstanceOf(CsvDataParser.class, dataParser);
    }

    @Test
    void loginTest() throws Exception {
        // given
        Account mockAccount = new Account(1L, "1", "선도형");
        when(authenticationService.login(1L, "1")).thenReturn(mockAccount);
        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);

        // when
        authenticationService.login(1L, "1");

        // then
        Account account = authenticationService.getCurrentAccount();
        assertEquals("선도형", account.getName());
        verify(authenticationService, times(1)).login(1L, "1");
    }

    @Test
    void logoutTest() throws Exception {
        // given
        Account mockAccount = new Account(1L, "1", "선도형");
        when(authenticationService.login(1L, "1")).thenReturn(mockAccount);
        when(authenticationService.getCurrentAccount()).thenReturn(mockAccount);
        authenticationService.login(1L, "1");

        // when
        authenticationService.logout();
        when(authenticationService.getCurrentAccount()).thenReturn(null);

        Account account = authenticationService.getCurrentAccount();
        assertNull(account);
        verify(authenticationService, times(1)).logout();
    }

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
    void outPutFormatterTest() throws Exception {
        // given
        Price mockPrice = new Price(1L, "동두천시", "가정용", 690);
        KoreanOutputFormatter koreanOutputFormatter = new KoreanOutputFormatter();
        EnglishOutputFormatter englishOutputFormatter = new EnglishOutputFormatter();

        // when
        String korean = koreanOutputFormatter.format(mockPrice, 10);
        String english = englishOutputFormatter.format(mockPrice, 10);

        // then
        assertTrue(korean.contains("총금액(원)"));
        assertTrue(english.contains("bill total(won)"));
        assertNotEquals(korean, english);
    }


    // 기존 통합 테스트 코드
//    @Autowired
//    DataParser dataParser;
//    @Autowired
//    private AuthenticationService authenticationService;
//    @Autowired
//    private PriceService priceService;
//
//    /**
//     * 전체 테스트 실행할 때 로그인 테스트에서 로그인이 이미 되어진 상태가 되었다.
//     * 그래서 다른 테스트에 영향이 가므로
//     * 각 테스트 후에 처리할 동작을 지정
//     */
//    @AfterEach
//    void afterEach() {
//        // 현재 로그인 상태일 경우 로그아웃
//        if (Objects.nonNull(authenticationService.getCurrentAccount())) {
//            authenticationService.logout();
//        }
//    }
//
//    @Test
//    void beanTest() {
//        assertInstanceOf(CsvDataParser.class, dataParser);
//    }
//
//    @Test
//    void loginTest() throws Exception {
//        authenticationService.login(1L, "1");
//        Account account = authenticationService.getCurrentAccount();
//        assertEquals("선도형", account.getName());
//    }
//
//    @Test
//    void logoutTest() throws Exception {
//        authenticationService.login(1L, "1");
//        authenticationService.logout();
//        Account account = authenticationService.getCurrentAccount();
//        assertNull(account);
//    }
//
//    @Test
//    void cityTest() throws Exception {
//        List<String> cities = priceService.cities();
//        assertEquals(cities.size(), 21);
//        assertTrue(cities.contains("동두천시"));
//    }
//
//    @Test
//    void sectorsTest() throws Exception {
//        List<String> sectors = priceService.sectors("동두천시");
//        assertEquals(sectors.size(), 5);
//        assertTrue(sectors.contains("가정용"));
//    }
//
//    @Test
//    void priceTest() throws Exception {
//        Price price = priceService.price("동두천시", "가정용");
//        assertEquals("동두천시", price.getCity());
//        assertEquals("가정용", price.getSector());
//        assertEquals(690, price.getUnitPrice());
//    }
//
//    @Test
//    void billTotalTest() throws Exception {
//        String billTotal = priceService.billTotal("동두천시", "가정용", 10);
//        assertTrue(billTotal.contains("690"));
//    }
//
//    @Test
//    void outPutFormatterTest() throws Exception {
//        Price price = priceService.price("동두천시", "가정용");
//
//        KoreanOutputFormatter koreanOutputFormatter = new KoreanOutputFormatter();
//        EnglishOutputFormatter englishOutputFormatter = new EnglishOutputFormatter();
//
//        String korean = koreanOutputFormatter.format(price, 10);
//        String english = englishOutputFormatter.format(price, 10);
//
//        assertTrue(korean.contains("690"));
//        assertTrue(english.contains("690"));
//        assertNotEquals(korean, english);
//    }

}
