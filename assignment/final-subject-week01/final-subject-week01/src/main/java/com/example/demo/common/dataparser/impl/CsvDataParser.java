package com.example.demo.common.dataparser.impl;

import com.example.demo.account.dto.Account;
import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.properties.FileProperties;
import com.example.demo.price.dto.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



@Slf4j
public class CsvDataParser implements DataParser {
    /**
     * 현재 @configuration를 설정한 Config 클래스에서 빈을 등록하는 방식을 사용했으므로
     * 여기서 주입을 하면 안된다!!
     *
     * 여기서 주입할 때는 @Component 방식일 때
     */
    private FileProperties fileProperties;

    private String ACCOUNT_FILE_NAME;
    private String PRICE_FILE_NAME;

    /**
     * 즉, 외부 프로퍼티 값을 받은 FileProperties를 파라미터로 받아서 주입해야한다.
     */
    public CsvDataParser(FileProperties fileProperties) {
        log.info("CsvDataParser 로 선택!");
        this.ACCOUNT_FILE_NAME = fileProperties.getAccountPath();
        this.PRICE_FILE_NAME = fileProperties.getPricePath();
    }

    InputStream getFileAsStream(String fileName) {
        return getClass().getClassLoader().getResourceAsStream(fileName);
    }


    public List<String> cities() {
        List<String> cityList = new ArrayList<>();

        InputStream priceFileAsStreamData = getFileAsStream(PRICE_FILE_NAME);
        try (CSVParser csvParser = CSVParser.parse(priceFileAsStreamData, StandardCharsets.UTF_8, CSVFormat.EXCEL)) {
            List<CSVRecord> csvRecordList = csvParser.getRecords();

            for (int i = 1; i < csvRecordList.size(); i++) {
                String cityName = csvRecordList.get(i).get(1).trim();

                if (!cityList.contains(cityName)) {
                    cityList.add(cityName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cityList;
    }

    public List<String> sectors(String city) {
        List<String> sectorList = new ArrayList<>();

        InputStream priceFileAsStreamData = getFileAsStream(PRICE_FILE_NAME);
        try (CSVParser csvParser = CSVParser.parse(priceFileAsStreamData, StandardCharsets.UTF_8, CSVFormat.EXCEL)) {
            List<CSVRecord> csvRecordList = csvParser.getRecords();

            for (int i = 1; i < csvRecordList.size(); i++) {
                String cityName = csvRecordList.get(i).get(1).trim();
                String sectorName = csvRecordList.get(i).get(2).trim();

                if (cityName.equals(city) && !sectorList.contains(sectorName)) {
                    sectorList.add(sectorName);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sectorList;
    }

    public Price price(String city, String sector) {
        InputStream priceFileAsStreamData = getFileAsStream(PRICE_FILE_NAME);
        try (CSVParser csvParser = CSVParser.parse(priceFileAsStreamData, StandardCharsets.UTF_8, CSVFormat.EXCEL)) {
            List<CSVRecord> csvRecordList = csvParser.getRecords();

            for (int i = 1; i < csvRecordList.size(); i++) {
                long recordId = Long.parseLong(csvRecordList.get(i).get(0));
                String recordCity = csvRecordList.get(i).get(1).trim();
                String recordSector = csvRecordList.get(i).get(2).trim();
                int recordUnitPrice = Integer.parseInt(csvRecordList.get(i).get(6));

                if (recordCity.equals(city) && recordSector.equals(sector)) {
                    return new Price(recordId, recordCity, recordSector, recordUnitPrice);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Account> accounts() {
        List<Account> accountList = new ArrayList<>();

        InputStream accountFileAsStreamData = getFileAsStream(ACCOUNT_FILE_NAME);
        try (CSVParser csvParser = CSVParser.parse(accountFileAsStreamData, StandardCharsets.UTF_8, CSVFormat.EXCEL)) {
            List<CSVRecord> csvRecordList = csvParser.getRecords();

            for (int i = 1; i < csvRecordList.size(); i++) {
                CSVRecord csvRecord = csvRecordList.get(i);

                long id = Long.parseLong(csvRecord.get(0));
                String password = csvRecord.get(1).trim();
                String name = csvRecord.get(2).trim();

                Account account = new Account(id, password, name);
                accountList.add(account);
            }

            return accountList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
