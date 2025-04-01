package org.example.student;


import org.springframework.beans.factory.annotation.Value;

public class UniversityStudent implements Student {

    @Value("${university.identity}")
    String identity;

    @Override
    public void identity() {
        System.out.println(identity);
    }

}
