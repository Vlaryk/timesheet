package com.example.timesheet.service;

import com.example.timesheet.Page.TimesheetPageDto;
import com.example.timesheet.client.ProjectResponse;
import com.example.timesheet.client.TimesheetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {
    private final DiscoveryClient discoveryClient;

    private RestClient restClient() {
        List<ServiceInstance> instances = discoveryClient.getInstances("TIMESHEET-REST");
        int size = instances.size();
        int instanceIndex = ThreadLocalRandom.current().nextInt(0, size);
        ServiceInstance instance = instances.get(instanceIndex);
        String uri = "http://" + instance.getHost() + ":" + instance.getPort();
        return RestClient.create(uri);
    }


    public Optional<TimesheetPageDto> getById(Long id) {
        try {
            TimesheetResponse timesheet = restClient().get()
                    .uri("/timesheets/" + id)
                    .retrieve()
                    .body(TimesheetResponse.class);
            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
            timesheetPageDto.setTimesheetId(String.valueOf(timesheet.getId()));
            timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDto.setTimesheetCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
            ProjectResponse project = restClient().get()
                    .uri("/projects/" + timesheet.getProjectId())
                    .retrieve()
                    .body(ProjectResponse.class);
            timesheetPageDto.setProjectName(project.getName());
            return Optional.of(timesheetPageDto);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    public List<TimesheetPageDto> getAll () {
        List<TimesheetResponse> timesheets = restClient().get()
                .uri("/timesheets")
                .retrieve()
                .body(new ParameterizedTypeReference<List<TimesheetResponse>>() {
                });
        List<TimesheetPageDto> result = new ArrayList<>();

        for (TimesheetResponse timesheet : timesheets) {
            TimesheetPageDto timesheetPageDto = new TimesheetPageDto();
            timesheetPageDto.setTimesheetId(String.valueOf(timesheet.getId()));
            timesheetPageDto.setTimesheetMinutes(String.valueOf(timesheet.getMinutes()));
            timesheetPageDto.setTimesheetCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
            ProjectResponse project = restClient().get()
                    .uri("/projects/" + timesheet.getProjectId())
                    .retrieve()
                    .body(ProjectResponse.class);
            timesheetPageDto.setProjectName(project.getName());
            result.add(timesheetPageDto);
        }
        return result;
    }

    //FIXME обавить конвертер

}
