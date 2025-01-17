package com.fmordev.employeeservice.service.impl;

import com.fmordev.employeeservice.client.DepartmentAPIClient;
import com.fmordev.employeeservice.client.OrganizationAPIClient;
import com.fmordev.employeeservice.dto.DepartmentDto;
import com.fmordev.employeeservice.dto.EmployeeDto;
import com.fmordev.employeeservice.dto.OrganizationDto;
import com.fmordev.employeeservice.dto.ResponseApiDto;
import com.fmordev.employeeservice.entity.EmployeeEntity;
import com.fmordev.employeeservice.exception.ResourceNotFoundException;
import com.fmordev.employeeservice.mappers.EmployeeMapper;
import com.fmordev.employeeservice.repository.EmployeeRepository;
import com.fmordev.employeeservice.service.EmployeeService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final DepartmentAPIClient departmentApiClient;
  private final OrganizationAPIClient organizationApiClient;

  @Override
  public EmployeeDto save(EmployeeDto employeeDto) {
    EmployeeEntity employee = employeeRepository.save(EmployeeMapper.MAPPER.toEntity(employeeDto));
    return EmployeeMapper.MAPPER.toDto(employee);
  }

  //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getByIdFallback")
  @Retry(name = "${spring.application.name}", fallbackMethod = "getByIdFallback")
  @Override
  public ResponseApiDto getById(Long id) {
    log.info("Inside getById method");
    EmployeeDto employeeDto = EmployeeMapper.MAPPER.toDto(employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id)));

    DepartmentDto department = departmentApiClient.getByCode(employeeDto.getDepartmentCode());
    OrganizationDto organization = organizationApiClient.getByCode(employeeDto.getOrganizationCode());

    return ResponseApiDto.builder()
        .employee(employeeDto)
        .department(department)
        .organization(organization)
        .build();
  }

  private ResponseApiDto getByIdFallback(Long id, Throwable throwable) {
    log.info("Inside fallback method");
    EmployeeDto employeeDto = EmployeeMapper.MAPPER.toDto(employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id)));

    return ResponseApiDto.builder()
        .employee(employeeDto)
        .department(DepartmentDto.builder()
            .code("R&D001")
            .name("R&D Department")
            .description("Research and Development Department")
            .build())
        .organization(OrganizationDto.builder()
            .code("ORG001")
            .name("Organization")
            .description("Organization Description")
            .build())
        .build();
  }
}
