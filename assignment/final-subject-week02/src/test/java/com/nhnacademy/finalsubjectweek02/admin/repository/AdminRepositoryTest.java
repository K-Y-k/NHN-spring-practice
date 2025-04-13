package com.nhnacademy.finalsubjectweek02.admin.repository;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;
import com.nhnacademy.finalsubjectweek02.admin.repository.impl.AdminRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AdminRepositoryTest {

    private final AdminRepositoryImpl adminRepository = new AdminRepositoryImpl();

    Admin sampleAdmin = new Admin("cs1", "123", "cs");

    @Test
    @DisplayName("관리자 찾기 테스트")
    void findById() {
        Admin admin = adminRepository.findById(sampleAdmin.getId());

        assertThat(admin).isNotNull();
        assertThat(admin.getId()).isEqualTo("cs1");
        assertThat(admin.getName()).isEqualTo("CS담당자1");
    }

    @Test
    @DisplayName("관리자 찾기 테스트 - 실패(존재하지 않는 관리자 ID)")
    void findById_NotExist() {
        Admin admin = adminRepository.findById("x");

        assertThat(admin).isNull();
    }

    @Test
    @DisplayName("관리자 존재 여부 테스트")
    void existsAdmin() {
        boolean exists = adminRepository.existsAdmin(sampleAdmin.getId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("관리자 존재 여부 테스트 - 실패(존재하지 않음)")
    void existsAdmin_False() {
        boolean exists = adminRepository.existsAdmin("x");

        assertThat(exists).isFalse();
    }
}
