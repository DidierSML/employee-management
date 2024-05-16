package com.example.employeemanagement.dto;

import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    Employee RequestToEntity (EmployeeRequest employeeRequest);
    EmployeeResponse EntityToResponse (Employee employee);
    List <EmployeeResponse> fromListEntityToListResponse (List <Employee> employeeList);

}
