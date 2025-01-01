package com.fmordev.employeeservice.service;

import com.fmordev.employeeservice.dto.EmployeeDto;
import com.fmordev.employeeservice.dto.ResponseApiDto;

public interface EmployeeService {
  EmployeeDto save(EmployeeDto employeeDto);
  ResponseApiDto getById(Long id);
}

