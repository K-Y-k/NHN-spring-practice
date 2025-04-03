package com.example.demo.price.formatter;

import com.example.demo.price.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EnglishOutputFormatter implements OutPutFormatter {

    @Override
    public String format(Price price, int usage) {
        return "city: " + price.getCity() + ", sector:" + price.getSector() + ", unit price(won): " + price.getUnitPrice() + ", bill total(won)" + price.getUnitPrice() * usage;
    }

}
