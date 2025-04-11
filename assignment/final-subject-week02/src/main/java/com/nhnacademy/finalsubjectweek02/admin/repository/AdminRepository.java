package com.nhnacademy.finalsubjectweek02.admin.repository;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;

public interface AdminRepository {
    Admin findById(String id);
    boolean existsAdmin(String id);
}
