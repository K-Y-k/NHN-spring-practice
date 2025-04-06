package com.example.demo.aop;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.example.demo.account.service.AuthenticationService;
import com.example.demo.shell.MyCommands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AopTestUtils;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=price.csv",
        "file.account-path=account.csv"
})
public class PriceAopTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private MyCommands myCommands;

    private ListAppender<ILoggingEvent> listAppender;


    @BeforeEach
    public void setUp() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        listAppender = new ListAppender<>();
        listAppender.setContext(loggerContext);
        listAppender.start();
        loggerContext.getLogger("com.example.demo.price.aop.PriceAop").addAppender(listAppender);
    }

    @AfterEach
    public void afterEach() {
        if (Objects.nonNull(authenticationService.getCurrentAccount())) {
            authenticationService.logout();
        }
    }

    @Test
    public void testNotLogin() {
        assertThrows(Exception.class, () -> myCommands.city());
    }

    @Test
    public void testLogout() {
        // given
        authenticationService.login(1L, "1");

        // when
        authenticationService.logout();

        // then
        assertThrows(Exception.class, () -> myCommands.city());
    }

    @Test
    public void testLogin() {
        // given when
        authenticationService.login(1L, "1");

        // then
        String cities = myCommands.city();
        assertTrue(cities.contains("동두천시"));
    }

    @Test
    public void testLoggingAspect() {
        // given
        authenticationService.login(1L, "1");
        myCommands.sector("동두천시");

        // when
        List<ILoggingEvent> logsList = listAppender.list;
        assertThat(logsList).isNotEmpty();

        // then
        assertTrue(logsList.getFirst().getFormattedMessage().contains("선도형"));
        assertTrue(logsList.getFirst().getFormattedMessage().contains("동두천시"));
    }

    @Test
    public void isAop() {
        assertTrue(AopUtils.isAopProxy(myCommands));
    }

    @Test
    public void testNonAop() {
        authenticationService.login(1L, "1");

        MyCommands nonAopCommands = AopTestUtils.getTargetObject(myCommands);
        assertFalse(AopUtils.isAopProxy(nonAopCommands));

        String cities = nonAopCommands.city();
        assertTrue(cities.contains("동두천시"));
    }
}