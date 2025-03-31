package org.example.student;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HighSchoolStudent implements Student {

    //TODO-1 주입하고 싶은 객체에 대해 생성자를 만든다.
    private final Inner inner;

    @Override
    public void identity() {
        inner.innerMethod();
    }

}
