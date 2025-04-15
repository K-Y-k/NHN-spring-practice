package com.nhnacademy.daily.controller;

import com.nhnacademy.daily.model.Project;
import com.nhnacademy.daily.service.ProjectServiceRedis;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectServiceRedis projectService;

    @GetMapping("/projects/{code}")
    public Project getProject(@PathVariable String code){
        return projectService.getProject(code);
    }

    @GetMapping("/projects")
    public Page<Project> getProjects(Pageable pageable){
        return projectService.getProjects(pageable);
    }

    /**
     * 멤버를 프로젝트에 추가하는 API 작성
     */
    @PostMapping("/projects/{code}/members/{memberId}")
    public String addProjectFromMember(@PathVariable String code, @PathVariable String memberId){
        return "";
    }

    /**
     * 프로젝트 멤버 목록을 조회하는 API 작성
     */
    @GetMapping("/projects/{code}/members")
    public String getProjectsFromMember(@PathVariable String code){
        return "";
    }
}
