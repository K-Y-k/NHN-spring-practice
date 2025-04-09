package com.nhnacademy.springbootmvc.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * String <-> LocalDate 양방향으로 변환하도록 직접 구현한 formatter
 */
@Component
public class LocalDateFormatter implements Formatter<LocalDate> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // String -> LocalDate
    @Override
    public LocalDate parse(String text, Locale locale) {
        return LocalDate.parse(text, formatter);
    }

    // LocalDate -> String
    @Override
    public String print(LocalDate object, Locale locale) {
        return object.format(formatter);
    }
}
