package com.fmordev.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationDto {
  private Long id;
  private String name;
  private String description;
  private String code;
  private LocalDateTime createdAt;
}
