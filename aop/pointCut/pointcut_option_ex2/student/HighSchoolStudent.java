package org.example.student;


import org.example.annotation.CustomAnnotation;

public class HighSchoolStudent implements Student {

    /**
     * 사용자 어노테이션을 붙임
     */
    @CustomAnnotation
    @Override
    public void identity() {
        System.out.println("미성년자");
    }

}
