package com.example.demo.format;

import com.example.demo.price.formatter.KoreanOutputFormatter;
import com.example.demo.price.formatter.OutPutFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest(properties = {
        "spring.shell.interactive.enabled=false",
        "file.type=csv",
        "file.price-path=price.csv",
        "file.account-path=account.csv"
})
@ActiveProfiles("kor")
public class KorProfileTest {

    @Autowired
    private OutPutFormatter outPutFormatter;


    @Test
    void beanTest() {
        assertInstanceOf(KoreanOutputFormatter.class, outPutFormatter);
    }

}
