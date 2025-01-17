package com.fmordev.employeeservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {
  private Long id;
  private String name;
  private String description;
  private String code;
}
