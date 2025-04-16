package com.nhnacademy.daily.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.nhnacademy.daily.custom.CustomLocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String code;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate localDate;

    private ProjectType type;
}