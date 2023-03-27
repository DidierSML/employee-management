package com.example.employeemanagement.service.Impl;

import com.example.employeemanagement.dto.EmployeeMapper;
import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeEntity> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<EmployeeEntity> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    //Using - Request and Response - with save and update employee

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) { //Un objeto saveEmployee va a ser igual a una Entidad que recibio un request y lo guardo como entidad, es decir va a ser una copia que retornara la misma info

        EmployeeEntity employeeEntity = EmployeeMapper.MAPPER.RequestToEntity(employeeRequest);
        employeeRepository.save(employeeEntity);

        return EmployeeMapper.MAPPER.EntityToResponse(employeeEntity);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest, Long id) {

        Optional <EmployeeEntity> checkingExistingEmployee = findById(id);
            if (! checkingExistingEmployee.isPresent())
                throw new RuntimeException("Employee Id " + id + " Not Found!");

            EmployeeEntity employeeEntity = EmployeeMapper.MAPPER.RequestToEntity(employeeRequest);
            employeeEntity.setId(id);
            employeeRepository.save(employeeEntity);

            return  EmployeeMapper.MAPPER.EntityToResponse(employeeEntity);
    }
}
