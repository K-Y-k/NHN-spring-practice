package com.nhnacademy.finalsubjectweek02.admin.repository.impl;

import com.nhnacademy.finalsubjectweek02.admin.domain.Admin;
import com.nhnacademy.finalsubjectweek02.admin.repository.AdminRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private final Map<String, Admin> adminMap = new HashMap<>();

    public AdminRepositoryImpl() {
        adminMap.put("cs1", new Admin("cs1", "123", "CS담당자1"));
        adminMap.put("cs2", new Admin("cs2", "123", "CS담당자2"));
        adminMap.put("cs3", new Admin("cs3", "123", "CS담당자3"));
        adminMap.put("cs4", new Admin("cs4", "123", "CS담당자4"));
        adminMap.put("zxc", new Admin("zxc", "zxc", "CS담당자5"));
    }

    @Override
    public Admin findById(String id) {
        Admin findAdmin = adminMap.get(id);
        if (Objects.isNull(findAdmin)) {
            return null;
        }

        return findAdmin;
    }

    @Override
    public boolean existsAdmin(String id) {
        return adminMap.containsKey(id);
    }
}
