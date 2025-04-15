//package com.nhnacademy.daily.service;
//
//import com.nhnacademy.daily.exception.MemberAlreadyExistsException;
//import com.nhnacademy.daily.exception.ProjectNotFoundException;
//import com.nhnacademy.daily.messenger.DoorayMessengerRequest;
//import com.nhnacademy.daily.model.Member;
//import com.nhnacademy.daily.model.Project;
//import com.nhnacademy.daily.model.ProjectDto;
//import com.nhnacademy.daily.model.ProjectType;
//import jakarta.annotation.PostConstruct;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.*;
//
//@Service
//public class ProjectServiceHashMap {
//    private final Map<String, Project> projectMap = new HashMap<>();
//
//
//    @PostConstruct
//    public void init() {
//        saveProject(new ProjectDto("black&white", LocalDate.now(), ProjectType.PUBLIC));
//        saveProject(new ProjectDto("red&blue", LocalDate.now(), ProjectType.PRIVATE));
//        saveProject(new ProjectDto("yellow&green", LocalDate.now(), ProjectType.PRIVATE));
//    }
//
//
//    public Project saveProject(ProjectDto projectDto) {
//        if (projectMap.containsKey(projectDto.getCode())) {
//            throw new MemberAlreadyExistsException("Member already exists.");
//        }
//
//        Project createProject = new Project(projectDto.getCode(), projectDto.getLocalDate(), projectDto.getType());
//        projectMap.put(createProject.getCode(), createProject);
//
//        return createProject;
//    }
//
//    public Project getProject(String code){
//        Project findProject = projectMap.get(code);
//        if (Objects.isNull(findProject)) {
//            throw new ProjectNotFoundException("Project not found");
//        }
//
//        return findProject;
//    }
//
//    public Page<Project> getProjects(Pageable pageable) {
//        List<Project> projectList = new ArrayList<>(projectMap.values());
//
//        int start = (int) pageable.getOffset();
//        int end = Math.min(start + pageable.getPageSize(), projectList.size());
//        List<Project> pagedList = projectList.subList(start, end);
//
//        return new PageImpl<>(pagedList, pageable, projectList.size());
//    }
//}
