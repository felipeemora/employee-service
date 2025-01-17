package com.fmordev.employeeservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
  private Long id;
  private String name;
  private String lastName;
  private String email;
  private String departmentCode;
  private String organizationCode;
}
