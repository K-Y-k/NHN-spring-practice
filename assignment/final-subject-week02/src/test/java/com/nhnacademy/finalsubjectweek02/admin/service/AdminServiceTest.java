package com.nhnacademy.finalsubjectweek02.admin.service;


import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;
import com.nhnacademy.finalsubjectweek02.admin.exception.AdminNotFoundException;
import com.nhnacademy.finalsubjectweek02.admin.repository.AdminRepository;
import com.nhnacademy.finalsubjectweek02.admin.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    private final String ADMIN_ID = "cs1";
    private final String ADMIN_PASSWORD = "123";
    private final Admin admin = new Admin(ADMIN_ID, ADMIN_PASSWORD, "CS담당자1");


    @Test
    @DisplayName("관리자 조회 테스트")
    void getAdmin() {
        when(adminRepository.findById(ADMIN_ID)).thenReturn(admin);

        Admin result = adminService.getAdmin(ADMIN_ID);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("cs1");
        assertThat(result.getPassword()).isEqualTo("123");

        verify(adminRepository, times(2)).findById(ADMIN_ID);
    }

    @Test
    @DisplayName("관리자 조회 테스트 - 실패(존재하지 않는 관리자 ID)")
    void getAdmin_NotExists() {
        when(adminRepository.findById("x")).thenReturn(null);

        assertThatThrownBy(() -> adminService.getAdmin("x"))
                .isInstanceOf(AdminNotFoundException.class);

        verify(adminRepository).findById("x");
    }

    @Test
    @DisplayName("ID와 비밀번호 매칭 테스트")
    void match() {
        when(adminRepository.findById(ADMIN_ID)).thenReturn(admin);

        boolean result = adminService.match(ADMIN_ID, ADMIN_PASSWORD);

        assertThat(result).isTrue();
        verify(adminRepository).findById(ADMIN_ID);
    }

    @Test
    @DisplayName("ID와 비밀번호 일치 테스트 - 실패(Id, 비밀번호 불일치)")
    void match_Failure_Invalid() {
        when(adminRepository.findById(ADMIN_ID)).thenReturn(admin);

        boolean result1 = adminService.match(ADMIN_ID, "x");
        boolean result2 = adminService.match("x", admin.getPassword());
        boolean result3 = adminService.match("x", "x");

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        verify(adminRepository).findById(ADMIN_ID);
    }

    @Test
    @DisplayName("존재하는 관리자 테스트")
    void existsAdmin() {
        when(adminRepository.existsAdmin(ADMIN_ID)).thenReturn(true);

        boolean result = adminService.existsAdmin(ADMIN_ID);

        assertThat(result).isTrue();
        verify(adminRepository).existsAdmin(ADMIN_ID);
    }

    @Test
    @DisplayName("존재하는 관리자 테스트 - 실패(존재하지 않는 관리자 ID)")
    void existsAdmin_False() {
        when(adminRepository.existsAdmin("x")).thenReturn(false);

        boolean result = adminService.existsAdmin("x");

        assertThat(result).isFalse();
        verify(adminRepository).existsAdmin("x");
    }
}
