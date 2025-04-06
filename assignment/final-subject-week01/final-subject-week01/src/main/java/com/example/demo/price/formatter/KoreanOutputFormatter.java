package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Slf4j
@Profile("kor")
@Component
public class KoreanOutputFormatter implements OutPutFormatter {

    @Override
    public String format(Price price, int usage) {
        return "지자체명: " + price.getCity() + ", 업종: " + price.getSector() + ", 구간금액(원): " + price.getUnitPrice() + ", 총금액(원): " + price.getUnitPrice() * usage;
    }
}
