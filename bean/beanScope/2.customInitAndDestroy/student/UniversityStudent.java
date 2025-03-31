package org.example.student;


//TODO-1 initMethod, destroyMethod 를 만들어준다.
public class UniversityStudent implements Student {

    @Override
    public void identity() {
        System.out.println("성인");
    }

    /**
     * 방식1 : 해당 클래스에서의 메소드로 빈 생명주기 메소드로 적용 방식
     */
    /**
     * 해당 클래스에서의 메소드로 빈 생명주기 메소드 커스텀
     */
    public void customInit() {
        System.out.println("UniversityStudent 빈 생성");
    }
    public void customDestroy() {
        System.out.println("UniversityStudent 빈 소멸");
    }
}
