package com.example.demo.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@Getter
@ConfigurationProperties("file")
public class FileProperties {
    private String type;
    private String pricePath;
    private String accountPath;
}
