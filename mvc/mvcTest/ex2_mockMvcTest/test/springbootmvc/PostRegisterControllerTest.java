package com.nhnacademy.springbootmvc;

import com.nhnacademy.springbootmvc.controller.PostRegisterController;
import com.nhnacademy.springbootmvc.exception.PostNotFoundException;
import com.nhnacademy.springbootmvc.exception.UserNotFoundException;
import com.nhnacademy.springbootmvc.exception.ValidationFailedException;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import com.nhnacademy.springbootmvc.validator.PostRegisterRequestValidator;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @WebMvcTest()는 () 내부에 선언 클래스를 제외하고 모두 비활성화 시켜주므로
 * @ContextConfiguration으로 활성화 시킬 클래스를 선언한 것
 *
 * 여기 테스트에서는 PostRepository도 사용되어
 * @ContextConfiguration에서 PostRepository.class, PostRegisterRequestValidator.class 선언
 */
@WebMvcTest(PostRegisterController.class)
@ContextConfiguration(classes = {PostRegisterRequestValidator.class})
public class PostRegisterControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        PostRepository postRepository = mock(PostRepository.class);
        PostRegisterRequestValidator validator = new PostRegisterRequestValidator();

        PostRegisterController controller = new PostRegisterController(postRepository, validator);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setValidator(validator)
                .build();
    }

    @Test
    void testValidation() {
        Throwable th = catchThrowable(() ->
                        mockMvc.perform(post("/post/register")
                              .param("title", "")
                              .param("content", ""))
                      .andDo(print()));

        assertThat(th).isInstanceOf(ServletException.class)
                .hasCauseInstanceOf(ValidationFailedException.class);
    }
}
