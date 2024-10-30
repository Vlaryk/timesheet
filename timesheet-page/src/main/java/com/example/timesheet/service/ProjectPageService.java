package com.example.timesheet.service;

//import com.example.timesheet.Page.ProjectPageDto;
//import com.example.timesheet.model.Project;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class ProjectPageService {
//    private final ProjectService service;
//
//    public Optional<ProjectPageDto> getProjectById(Long id) {
//        return service.getById(id).map(this::convert);
//    }
//
//    public List<ProjectPageDto> getAllProjects() {
//        return service.getAll().stream().map(this::convert).toList();
//    }
//
//
//
//    private ProjectPageDto convert(Project project) {
//        ProjectPageDto projectPageDto = new ProjectPageDto();
//        projectPageDto.setId(String.valueOf(project.getId()));
//        projectPageDto.setName(project.getName());
//        return projectPageDto;
//    }
//}
