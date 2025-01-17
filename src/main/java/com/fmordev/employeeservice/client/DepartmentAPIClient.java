package com.fmordev.employeeservice.client;

import com.fmordev.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentAPIClient {
  @GetMapping(value = "api/departments/by-code/{code}")
  DepartmentDto getByCode(@PathVariable("code") String code);
}
