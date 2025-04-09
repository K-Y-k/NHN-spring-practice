package com.nhnacademy.springbootmvc;

import com.nhnacademy.springbootmvc.controller.PostController;
import com.nhnacademy.springbootmvc.controller.UserController;
import com.nhnacademy.springbootmvc.exception.PostNotFoundException;
import com.nhnacademy.springbootmvc.repository.PostRepository;
import com.nhnacademy.springbootmvc.repository.PostRepositoryImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(PostController.class)
@ContextConfiguration(classes = {PostRepository.class})
public class PostControllerTest {
    private MockMvc mockMvc;

    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new PostController(postRepository))
                .build();
    }

    @Test
    void testPostNotFound() throws Exception {
        when(postRepository.getPost(anyLong())).thenReturn(null);

        Throwable th = catchThrowable(()->mockMvc.perform(get("/post/{postId}", 1L)));

        assertThat(th).isInstanceOf(ServletException.class)
                .hasCauseInstanceOf(PostNotFoundException.class);
    }
}
