package com.nhnacademy.subjectweek02.controller;

import com.nhnacademy.subjectweek02.domain.Student;
import com.nhnacademy.subjectweek02.domain.StudentRegisterRequest;
import com.nhnacademy.subjectweek02.exception.ValidationFailedException;
import com.nhnacademy.subjectweek02.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    /**
     * @ModelAttribute로 Student 객체를 모델에 담아 적용한 getPasswordMaskedStudent() 공통 메서드 적용
     * Request 요청 받기 전에 미리 호출되는 것이다!
     */
    @ModelAttribute("student")
    public Student getPasswordMaskedStudent(@PathVariable("studentId") String studentId) {
        return Student.constructPasswordMaskedStudent(studentRepository.getStudent(studentId));
    }

    @GetMapping("/{studentId}")
    public String viewForm() {
                       //(@CookieValue(value = "SESSION",required = false) String cookieSessionId,
                       // HttpServletRequest req) {
         // 로그인 체크 인터셉터 추가로 여러 시그니처 메소드에 반복되는 검증 코드 삭제
         // HttpSession session = req.getSession(false);
         // String sessionId = null;
         // if (Objects.nonNull(session)) {
         //     sessionId = (String) session.getAttribute("SESSION");
         // }
         // if (StringUtils.isEmpty(sessionId) || !sessionId.equals(cookieSessionId)) {
         //     return "redirect:/login";
         // }

        return "student/studentView";
    }

    /**
     * 점수와 평가 항목을 출력하지 않는 메서드
     * GET /student/{studentId}와는 다른 별도의 Controller Method로 작성
     */
    @GetMapping(value = "/{studentId}", params = "hideScore")
    public String viewFormHideScore(@RequestParam(required = false) String hideScore,
                                Model model) {
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
         *
         * 공통 메서드는 미리 호출한 것이므로 여기서 속성명이 겹치면 여기의 속성값으로 적용된다.
         */
        Student student = studentRepository.getStudent(studentId);

        ModelAndView mav = new ModelAndView("student/studentModify");
        mav.addObject("student", student);

        return mav;
    }

    @PostMapping("/{studentId}/modify")
    public String modify(@Valid @ModelAttribute StudentRegisterRequest studentRegisterRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String id = studentRegisterRequest.getId();
        String password = studentRegisterRequest.getPassword();
        String name = studentRegisterRequest.getName();
        String email = studentRegisterRequest.getEmail();
        int score = studentRegisterRequest.getScore();
        String comment = studentRegisterRequest.getComment();

        Student updateStudent = studentRepository.update(id, password, name, email, score, comment);

        return "redirect:/student/" + updateStudent.getId();
    }
}
