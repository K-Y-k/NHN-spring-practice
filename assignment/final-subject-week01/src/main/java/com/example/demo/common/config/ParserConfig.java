package com.example.demo.common.config;

import com.example.demo.common.dataparser.DataParser;
import com.example.demo.common.dataparser.impl.CsvDataParser;
import com.example.demo.common.dataparser.impl.JsonDataParser;
import com.example.demo.common.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FileProperties.class)
public class ParserConfig {

    @ConditionalOnProperty(name = "file.type", havingValue = "csv", matchIfMissing = false)
    @Bean
    public DataParser csvDataParser(FileProperties fileProperties) {
        return new CsvDataParser(fileProperties);
    }

    @ConditionalOnProperty(name = "file.type", havingValue = "json", matchIfMissing = false)
    @Bean
    public DataParser jsonDataParser(FileProperties fileProperties) {
        return new JsonDataParser(fileProperties);
    }
}
