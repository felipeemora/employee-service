package com.fmordev.employeeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseApiDto {
  private EmployeeDto employee;
  private DepartmentDto department;
}
