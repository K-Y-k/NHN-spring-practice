package com.nhnacademy.restcontrollerpractice.security.step2_customAndOAuth.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberCreateCommand {
    private String id;
    private String password;
    private String name;
    @JsonSerialize(using = ToStringSerializer.class)
    private Role role;
}
