package com.example.timesheet.service;
import com.example.timesheet.Page.EmployeePageDto;
import com.example.timesheet.client.EmployeeResponse;
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
public class EmployeePageService {
    private final DiscoveryClient discoveryClient;

    RestClient restClient() {
        List<ServiceInstance> instances = discoveryClient.getInstances("TIMESHEET-REST");
        ServiceInstance serviceInstance = instances.get(ThreadLocalRandom.current().nextInt(0, instances.size()));
        String uri = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        return RestClient.create(uri);
    }

    public Optional<EmployeePageDto> findById (Long id) {
        try {
            EmployeeResponse employeeResponse = restClient().get()
                    .uri("/employees/" + id)
                    .retrieve()
                    .body(EmployeeResponse.class);

            EmployeePageDto employeePageDto = convert(employeeResponse);
            return Optional.of(employeePageDto);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    public List<EmployeePageDto> findAll () {
        List<EmployeeResponse> list = restClient().get()
                .uri("/employees")
                .retrieve()
                .body(new ParameterizedTypeReference<List<EmployeeResponse>>(){});

        return list.stream().map(this::convert).toList();
    }
    private EmployeePageDto convert(EmployeeResponse employee) {
        EmployeePageDto employeePageDto = new EmployeePageDto();
        employeePageDto.setId(String.valueOf(employee.getId()));
        employeePageDto.setFirstName(employee.getFirstName());
        employeePageDto.setLastName(employee.getLastName());
        employeePageDto.setSalary(String.valueOf(employee.getSalary()));

        return employeePageDto;
    }


}
