package org.example.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("student")
@Getter
@Setter
public class StudentProperties {
    String firstName;
}
