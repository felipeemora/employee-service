package com.fmordev.employeeservice.client;

import com.fmordev.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationAPIClient {
  @GetMapping(value = "api/organizations/by_code/{code}")
  OrganizationDto getByCode(@PathVariable("code") String code);
}
