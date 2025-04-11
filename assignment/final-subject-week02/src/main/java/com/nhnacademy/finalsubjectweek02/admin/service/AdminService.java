package com.nhnacademy.finalsubjectweek02.admin.service;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;

public interface AdminService {
    Admin getAdmin(String id);
    boolean match(String id, String password);
    boolean existsAdmin(String id);
}
