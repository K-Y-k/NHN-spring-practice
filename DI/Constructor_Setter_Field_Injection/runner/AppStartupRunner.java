package org.example.runner;

import lombok.RequiredArgsConstructor;
import org.example.student.HighSchoolStudent;
import org.example.student.UniversityStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * 롬복 라이브러리에서 지원하는 방식
 * - 편리하게 생성자를 자동으로 생성해준다.
 * - 생성자 주입까지 해주는 것이 아니다. 그래서 주입을 위해 필드에 @Autowired가 필요하다.
 * - 하지만 Spring에서 @RequiredArgsConstructor를 사용하면,
 *   생성자를 통한 의존성 주입(생성자 주입)이 가능해서 @Autowired를 생략해도 된다. (@Autowired는 생성자가 1개면 자동으로 주입됨)
 * @RequiredArgsConstructor : final / non-nullfield argument 를 포함하는 생성자 자동 생성
 * @AllArgsConstructor          : 모든 argument 를 포함하는 생성자를 자동 생성
 * @NoArgsConstructor         : 기본 생성자(파라미터 없는 생성자) 자동 생성
 */
@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {

    //TODO-1 Constructor Injection
    private final HighSchoolStudent highSchoolStudent;
    private final UniversityStudent universityStudent;

    /**
     * 1.생성자 주입 방식
     * - 이 방식이 제일 좋은 이유는 final로 고정 시킬 수 있다.
     */
//    private final HighSchoolStudent highSchoolStudent;
//    private final UniversityStudent universityStudent;
//
//    @Autowired
//    public AppStartupRunner(HighSchoolStudent highSchoolStudent, UniversityStudent universityStudent) {
//        this.highSchoolStudent = highSchoolStudent;
//        this.universityStudent = universityStudent;
//    }

    /**
     * 2.Setter 주입 방식
     */
//    @Autowired
//    public void setHighSchoolStudent(HighSchoolStudent highSchoolStudent) {
//        this.highSchoolStudent = highSchoolStudent;
//    }
//    @Autowired
//    public void setUniversityStudent(UniversityStudent universityStudent) {
//        this.universityStudent = universityStudent;
//    }

    /**
     * 3.필드 주입 방식
     */
//    @Autowired
//    private HighSchoolStudent highSchoolStudent;
//    @Autowired
//    private UniversityStudent universityStudent;


    @Override
    public void run(ApplicationArguments args) {
        highSchoolStudent.identity();
        universityStudent.identity();
    }
}