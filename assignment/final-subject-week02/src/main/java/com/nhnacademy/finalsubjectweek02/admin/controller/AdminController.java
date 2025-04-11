package com.nhnacademy.finalsubjectweek02.admin.controller;

import com.nhnacademy.finalsubjectweek02.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class AdminController {
    private final AdminService adminService;


    @GetMapping("/admin/{adminId}")
    public String adminMain(@PathVariable String adminId) {
        return "user/adminMain";
    }

}
