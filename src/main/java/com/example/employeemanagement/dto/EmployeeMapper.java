package com.example.employeemanagement.dto;

import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    EmployeeEntity RequestToEntity (EmployeeRequest employeeRequest); //mapeo de Request a Entidad ->
    EmployeeResponse EntityToResponse (EmployeeEntity employeeEntity);//mapeo de Entidad a Response <-
}
