package com.nhnacademy.subjectweek02.controller;

import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.domain.StudentRegisterRequest;
import com.nhnacademy.subjectweek02.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    /**
     * @ModelAttribute로 getPasswordMaskedStudent() 공통 메서드 적용
     */
    @ModelAttribute("student")
    public Student getPasswordMaskedStudent(@PathVariable("studentId") String studentId) {
        return Student.constructPasswordMaskedStudent(studentRepository.getStudent(studentId));
    }

    @GetMapping("/{studentId}")
    public String view() {
        //Student student = studentRepository.getStudent(studentId);
        //model.put("student", Student.constructPasswordMaskedStudent(student));

        return "student/studentView";
    }

    /**
     * 점수와 평가 항목을 출력하지 않는 메서드
     * GET /student/{studentId}와는 다른 별도의 Controller Method로 작성
     */
    @GetMapping(value = "/{studentId}", params = "hideScore")
    public String viewHideScore(@RequestParam(required = false) String hideScore, Model model) {
        //Student student = studentRepository.getStudent(studentId);
        //model.put("student", Student.constructPasswordMaskedStudent(student));

        if (!StringUtils.isEmpty(hideScore) && hideScore.equals("yes")) {
            model.addAttribute("hideScore", hideScore);
        }

        return "student/studentView";
    }

    /**
     * ModelAndView 방식
     */
    @GetMapping("/{studentId}/modify")
    public ModelAndView modifyForm(@PathVariable String studentId) {
        /**
         * 비밀번호가 임시의 *****로 가져오면 
         * 변경하지 않고 제출할 때 의도하지 않은 ***** 값으로 저장되어
         * @ModelAttribute 기반의 getPasswordMaskedStudent() 공통 메서드를 사용하지 않음
         */
        Student student = studentRepository.getStudent(studentId);

        ModelAndView mav = new ModelAndView("student/studentModify");
        mav.addObject("student", student);

        return mav;
    }

    @PostMapping("/{studentId}/modify")
    public String modifyForm(@ModelAttribute StudentRegisterRequest studentRegisterRequest) {
        String id = studentRegisterRequest.getId();
        String password = studentRegisterRequest.getPassword();
        String name = studentRegisterRequest.getName();
        String email = studentRegisterRequest.getEmail();
        int score = studentRegisterRequest.getScore();
        String comment = studentRegisterRequest.getComment();

        Student updateStudent = studentRepository.update(id, password, name, email, score, comment);
        //model.addAttribute("student", Student.constructPasswordMaskedStudent(updateStudent));

        return "redirect:/student/" + updateStudent.getId();
    }
}
