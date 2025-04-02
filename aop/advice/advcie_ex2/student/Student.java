package org.example.student;

public interface Student {

    default String echo(String string) {
        return string;
    }

    String getSchool();
}
