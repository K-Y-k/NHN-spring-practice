package com.nhnacademy.springbootmvc.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * String <-> BigDecimal 양방향으로 변환하도록 직접 구현한 formatter
 */
@Component
public class MoneyFormatter implements Formatter<BigDecimal> {
    private static final DecimalFormat formatter = new DecimalFormat("#,###");

    //TODO 1: BigDecimal <-> String 변환하는 formatter 구현

    // String -> BigDecimal 변환 ex) 12,345 -> 12345
    @Override
    public BigDecimal parse(String text, Locale locale) throws ParseException {
        return new BigDecimal(text.replaceAll(",", ""));
    }

    // BigDecimal -> String 변환
    @Override
    public String print(BigDecimal object, Locale locale) {
        return formatter.format(object);
    }

}
