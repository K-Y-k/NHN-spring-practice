package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import org.springframework.stereotype.Component;

@Component
public interface OutPutFormatter {
    String format(Price price, int usage);
}
