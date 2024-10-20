package com.example.timesheet.controller;

import com.example.timesheet.service.ProjectPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {
    private final ProjectPageService service;

    @GetMapping("/{id}")
    public String getById (@PathVariable Long id, Model model) {
        Optional<ProjectPageDto> project = service.getProjectById(id);
        if (project.isEmpty()) {
            return "not-found.html";
        }
        model.addAttribute("project",project.get());
        return "project-page.html";
    }

//    public String getAllProjects (Model model) {
//        model.addAttribute("projects",List.copyOf(service.getAllProjects().stream().toList()));
//        return "projects-page.html";
//    }




}
