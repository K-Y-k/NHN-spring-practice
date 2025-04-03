package com.example.demo.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
public class Price {
    @JsonProperty("순번")
    long id;
    @JsonProperty("지자체명")
    String city;
    @JsonProperty("업종")
    String sector;
    @JsonProperty("구간금액(원)")
    int unitPrice;
}
