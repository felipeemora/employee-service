package com.fmordev.employeeservice.controller;

import com.fmordev.employeeservice.dto.EmployeeDto;
import com.fmordev.employeeservice.dto.ResponseApiDto;
import com.fmordev.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
    return new ResponseEntity<>(employeeService.save(employeeDto), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseApiDto> getById(@PathVariable Long id) {
    return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
  }
}
