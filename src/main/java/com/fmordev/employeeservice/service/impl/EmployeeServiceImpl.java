package com.fmordev.employeeservice.service.impl;

import com.fmordev.employeeservice.client.APIClient;
import com.fmordev.employeeservice.dto.DepartmentDto;
import com.fmordev.employeeservice.dto.EmployeeDto;
import com.fmordev.employeeservice.dto.ResponseApiDto;
import com.fmordev.employeeservice.entity.EmployeeEntity;
import com.fmordev.employeeservice.exception.ResourceNotFoundException;
import com.fmordev.employeeservice.mappers.EmployeeMapper;
import com.fmordev.employeeservice.repository.EmployeeRepository;
import com.fmordev.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final APIClient apiClient;


  public EmployeeServiceImpl(EmployeeRepository employeeRepository, APIClient apiClient) {
    this.employeeRepository = employeeRepository;
    this.apiClient = apiClient;
  }

  @Override
  public EmployeeDto save(EmployeeDto employeeDto) {
    EmployeeEntity employee = employeeRepository.save(EmployeeMapper.MAPPER.toEntity(employeeDto));
    return EmployeeMapper.MAPPER.toDto(employee);
  }

  @Override
  public ResponseApiDto getById(Long id) {
    EmployeeDto employeeDto = EmployeeMapper.MAPPER.toDto(employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id)));

    DepartmentDto department = apiClient.getByCode(employeeDto.getDepartmentCode());

    return ResponseApiDto.builder()
        .employee(employeeDto)
        .department(department)
        .build();
  }
}
