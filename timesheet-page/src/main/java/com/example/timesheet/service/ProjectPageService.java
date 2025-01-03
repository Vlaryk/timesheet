package com.example.timesheet.service;

import com.example.timesheet.Page.EmployeePageDto;
import com.example.timesheet.Page.ProjectPageDto;
import com.example.timesheet.client.EmployeeResponse;
import com.example.timesheet.client.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final DiscoveryClient discoveryClient;

    RestClient restClient() {
        List<ServiceInstance> instances = discoveryClient.getInstances("TIMESHEET-REST");
        ServiceInstance serviceInstance = instances.get(ThreadLocalRandom.current().nextInt(0, instances.size()));
        String uri = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return RestClient.create(uri);
    }

    public Optional<ProjectPageDto> getProjectById(Long id) {
        try {
            ProjectResponse projectResponse = restClient().get()
                    .uri("/projects/" + id)
                    .retrieve()
                    .body(ProjectResponse.class);

            ProjectPageDto projectPageDto = convert(projectResponse);
            return Optional.of(projectPageDto);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    public List<ProjectPageDto> getAllProjects() {
        List<ProjectResponse> list = restClient().get()
                .uri("/employees")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProjectResponse>>(){});

        return list.stream().map(this::convert).toList();
    }



    private ProjectPageDto convert(ProjectResponse project) {
        ProjectPageDto projectPageDto = new ProjectPageDto();
        projectPageDto.setId(String.valueOf(project.getId()));
        projectPageDto.setName(project.getName());
        return projectPageDto;
    }
}
