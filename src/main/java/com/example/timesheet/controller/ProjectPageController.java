package com.example.timesheet.controller;

import com.example.timesheet.service.ProjectPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/home/projects")
@RequiredArgsConstructor
public class ProjectPageController {
    private final ProjectPageService service;

    @GetMapping("/{id}")
    public String getById (@PathVariable Long id, Model model) {
        model.addAttribute("project",service.getProjectById(id));
        return "project-page.html";
    }

//    public String getAllProjects (Model model) {
//        model.addAttribute("projects",List.copyOf(service.getAllProjects().stream().toList()));
//        return "projects-page.html";
//    }




}
