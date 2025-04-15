package com.nhnacademy.daily.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.daily.exception.ProjectAlreadyExistsException;
import com.nhnacademy.daily.exception.ProjectNotFoundException;
import com.nhnacademy.daily.model.Project;
import com.nhnacademy.daily.model.ProjectDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProjectServiceRedis {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private final String PROJECT_HASH_NAME = "Project:";


    @PostConstruct
    public void initData() {
//        saveProject(new ProjectDto("black&white", LocalDate.now(), ProjectType.PUBLIC));
//        saveProject(new ProjectDto("red&blue", LocalDate.now(), ProjectType.PRIVATE));
//        saveProject(new ProjectDto("yellow&green", LocalDate.now(), ProjectType.PRIVATE));
    }


    public Project saveProject(ProjectDto projectDto) {
        if (redisTemplate.opsForHash().hasKey(PROJECT_HASH_NAME, projectDto.getCode())) {
            throw new ProjectAlreadyExistsException("Project already exists.");
        }

        Project createProject = new Project(projectDto.getCode(), projectDto.getLocalDate(), projectDto.getType());

        redisTemplate.opsForHash().put(PROJECT_HASH_NAME, createProject.getCode(), createProject);

        return createProject;
    }

    public Project getProject(String code) {
        Object o = redisTemplate.opsForHash().get(PROJECT_HASH_NAME, code);
        if (Objects.isNull(o)) {
            throw new ProjectNotFoundException("Project not found");
        }

        return objectMapper.convertValue(o, Project.class);
    }

    public Page<Project> getProjects(Pageable pageable) {
        List<Project> projectList = new ArrayList<>();

        Map<Object, Object> entries = redisTemplate.opsForHash().entries(PROJECT_HASH_NAME);
        for (Object o : entries.values()) {
            Project project = objectMapper.convertValue(o, Project.class);
            projectList.add(project);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), projectList.size());
        List<Project> pagedList = projectList.subList(start, end);

        return new PageImpl<>(pagedList, pageable, projectList.size());
    }
}
