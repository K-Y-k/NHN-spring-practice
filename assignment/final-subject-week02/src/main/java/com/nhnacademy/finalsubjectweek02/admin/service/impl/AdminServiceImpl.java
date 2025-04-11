package com.nhnacademy.finalsubjectweek02.admin.service.impl;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;
import com.nhnacademy.finalsubjectweek02.admin.exception.AdminNotFoundException;
import com.nhnacademy.finalsubjectweek02.admin.repository.AdminRepository;
import com.nhnacademy.finalsubjectweek02.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin getAdmin(String id) {
        Admin findAdmin = adminRepository.findById(id);
        if (Objects.isNull(findAdmin)) {
            throw new AdminNotFoundException("Admin with id " + id + " not found");
        }

        return adminRepository.findById(id);
    }

    @Override
    public boolean match(String id, String password) {
        return Optional.ofNullable(adminRepository.findById(id))
                .map(admin -> admin.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public boolean existsAdmin(String id) {
        return adminRepository.existsAdmin(id);
    }
}
