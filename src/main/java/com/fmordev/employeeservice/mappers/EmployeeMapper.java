package com.fmordev.employeeservice.mappers;

import com.fmordev.employeeservice.dto.EmployeeDto;
import com.fmordev.employeeservice.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

  EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

  EmployeeDto toDto(EmployeeEntity employeeEntity);
  EmployeeEntity toEntity(EmployeeDto employeeDto);
}
