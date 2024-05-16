package com.example.employeemanagement.service.Impl;

import com.example.employeemanagement.dto.EmployeeMapper;
import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public void EmployeeMapper (EmployeeMapper employeeMapper){

        this.employeeMapper = employeeMapper;
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {

        Employee employee = EmployeeMapper.MAPPER.RequestToEntity(employeeRequest);

        Employee newEmployee = employeeRepository.save(employee);

        return employeeMapper.EntityToResponse(newEmployee);
    }

    @Override
    public EmployeeResponse findById(Long id) {

        Optional<Employee> idEmployee = employeeRepository.findById(id);

        if (!idEmployee.isPresent()) {
            throw new RuntimeException("The Employee doesn't exist in our DB");
        } else {
            return employeeMapper.EntityToResponse(idEmployee.get());
        }

    }

    @Override
    public List<EmployeeResponse> findAllEmployee() {

        List <Employee> employeeList = employeeRepository.findAll();

        return employeeMapper.fromListEntityToListResponse(employeeList);
    }


    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest, Long id) {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);

        if (!existingEmployeeOptional.isPresent()) {
            throw new RuntimeException("Employee Id " + id + " Not Found!");
        } else {
            Employee existingEmployee = existingEmployeeOptional.get();

            // Actualizar los campos del empleado existente con los valores proporcionados en el request
            existingEmployee.setName(employeeRequest.getName());
            existingEmployee.setDateOfBirth(employeeRequest.getDateOfBirth());
            existingEmployee.setGender(employeeRequest.getGender());
            existingEmployee.setAddress(employeeRequest.getAddress());

            // Guardar el empleado actualizado en la base de datos
            Employee updatedEmployee = employeeRepository.save(existingEmployee);

            // Mapear y devolver la respuesta del empleado actualizado
            return employeeMapper.EntityToResponse(updatedEmployee);
        }
    }











    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
