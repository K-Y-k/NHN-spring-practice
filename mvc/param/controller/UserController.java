package com.nhnacademy.springbootmvc.controller;

import com.nhnacademy.springbootmvc.domain.User;
import com.nhnacademy.springbootmvc.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // TODO: 아래 요청을 처리하는 컨트롤러 메서드를 완성

    /* TODO 1
     * GET /users
     * 모든 저장된 멤버들을 출력
     */

    /* TODO 1-1 */
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userRepository.getUsers();
        model.addAttribute("users", users);
        return "users";
    }

    /** TODO 2
     * GET /user/{id}
     * id를 URL path variable 로 받아서 해당하는 유저를 조회
     */

    /* TODO 2-1 */
    @GetMapping("/user/{id}")
    public String getUser(Model model,
                          /* TODO 2-2 */@PathVariable("id") String id) { // 변수명과 동일하면 name 속성 필요 생략 가능)
        User user = userRepository.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }

    /** TODO 3
     * GET /user?id={id}
     * id를 request parameter 로 받아서 해당하는 유저를 조회
     */

    /* TODO 3-1 */
    @GetMapping("/user")
    public String getUserByName(Model model,
                                /* TODO 3-2 */@RequestParam(name = "id", required = false, defaultValue = "tom") String id) { // 변수명과 동일하면 name 속성 필요 생략 가능)
                                                     // required 속성으로 필수 지정가능, defaultValue 속성으로 기본 값 설정 가능
        User user = userRepository.getUser(id);
        model.addAttribute("user", user);
        return "user";
    }
}