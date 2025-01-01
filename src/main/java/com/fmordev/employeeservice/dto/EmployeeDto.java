package com.fmordev.employeeservice.dto;

import lombok.Data;

@Data
public class EmployeeDto {
  private Long id;
  private String name;
  private String lastName;
  private String email;
  private String departmentCode;
}
