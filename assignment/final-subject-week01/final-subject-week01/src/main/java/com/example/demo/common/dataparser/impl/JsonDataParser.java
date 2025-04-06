package com.example.demo.common.dataparser.impl;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class JsonDataParser implements DataParser {

    private FileProperties fileProperties;

    private final ObjectMapper objectMapper;
    private String FILE_PATH = "src\\main\\resources\\";
    File accountFile;
    File priceFile;


    public JsonDataParser(FileProperties fileProperties) {
        log.info("JsonDataParser 로 선택!");
        objectMapper = new ObjectMapper();
        this.accountFile = new File(FILE_PATH + fileProperties.getAccountPath());
        this.priceFile = new File(FILE_PATH + fileProperties.getPricePath());
    }


    public List<String> cities() {

        List<String> cityList = new ArrayList<>();

        if (!priceFile.exists()) {
            throw new IllegalArgumentException(priceFile.getName() + " 파일이 존재하지 않습니다.");
        }

        // json read & 역직렬화 (json string -> Object)
        try (FileInputStream fileInputStream = new FileInputStream(priceFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            List<Price> priceList = objectMapper.readValue(bufferedReader, new TypeReference<>() {});

            for (Price price : priceList) {
                String recordCity = price.getCity();

                if (!cityList.contains(recordCity)) {
                    cityList.add(recordCity);
                }
            }

            return cityList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> sectors(String city) {

        List<String> sectorList = new ArrayList<>();

        if (!priceFile.exists()) {
            throw new IllegalArgumentException(priceFile.getName() + " 파일이 존재하지 않습니다.");
        }

        try (FileInputStream fileInputStream = new FileInputStream(priceFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            List<Price> priceList = objectMapper.readValue(bufferedReader, new TypeReference<>() {});

            for (Price price : priceList) {
                String recordCity = price.getCity();
                String recordSector = price.getSector();

                if (recordCity.equals(city) && !sectorList.contains(recordSector)) {
                    sectorList.add(recordSector);
                }
            }

            return sectorList;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Price price(String city, String sector) {

        if (!priceFile.exists()) {
            throw new IllegalArgumentException(priceFile.getName() + " 파일이 존재하지 않습니다.");
        }

        try (FileInputStream fileInputStream = new FileInputStream(priceFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            List<Price> priceList = objectMapper.readValue(bufferedReader, new TypeReference<>() {});

            for (Price price : priceList) {
                long recordId = price.getId();
                String recordCity = price.getCity();
                String recordSector = price.getSector();
                int recordUnitPrice = price.getUnitPrice();

                if (recordCity.equals(city) && recordSector.equals(sector)) {
                    return new Price(recordId, recordCity, recordSector, recordUnitPrice);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Account> accounts() {

        if (!accountFile.exists()) {
            throw new IllegalArgumentException(accountFile.getName() + " 파일이 존재하지 않습니다.");
        }

        try (FileInputStream fileInputStream = new FileInputStream(accountFile);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            return objectMapper.readValue(bufferedReader, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
