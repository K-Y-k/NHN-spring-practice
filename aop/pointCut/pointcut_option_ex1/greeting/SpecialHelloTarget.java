package org.example.greeting;

import lombok.AllArgsConstructor;
import org.example.annotation.ParameterArgument;

@ParameterArgument
@AllArgsConstructor
public class SpecialHelloTarget {
    final String firstName;
    final String lastName;
}
