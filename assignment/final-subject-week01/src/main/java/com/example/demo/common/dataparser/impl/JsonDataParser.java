package com.example.demo.common.dataparser.impl;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
public class JsonDataParser implements DataParser {

    private final FileProperties fileProperties;

    public List<String> cities() {
        return null;
    }

    public List<String> sectors(String city) {
        return null;
    }

    public Price price(String city, String sector) {
        return null;
    }

    public List<Account> accounts() {
        return null;
    }


}
