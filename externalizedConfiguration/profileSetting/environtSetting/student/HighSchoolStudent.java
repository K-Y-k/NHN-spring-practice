package org.example.student;


import org.springframework.beans.factory.annotation.Value;

public class HighSchoolStudent implements Student {

    @Value("${high.identity}")
    String identity;

    @Override
    public void identity() {
        System.out.println(identity);
    }


}
