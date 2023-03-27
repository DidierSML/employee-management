package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.request.EmployeeRequest;
import com.example.employeemanagement.dto.response.EmployeeResponse;
import com.example.employeemanagement.entity.EmployeeEntity;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService ;


    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    @GetMapping("getAll")
    public List<EmployeeEntity> findAllEmployee () {
        return employeeService.findAllEmployee();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeEntity> findEmpolyeeById (@PathVariable("id") Long id){
        return employeeService.findById(id);
    }

    @PostMapping("save")
    public EmployeeEntity saveEmployee (@RequestBody EmployeeEntity employeeEntity){
        return employeeService.saveEmployee(employeeEntity);
    }

    @PutMapping("update")
    public EmployeeEntity updateEmployee (@RequestBody EmployeeEntity employeeEntity){
        return employeeService.updateEmployee(employeeEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
    }

    //Using - Request and Response - with save and update employee

    @PostMapping("/saveReqToEntity") // de Req a Entity y guarda = Entity = Response y retorna una copia de lo guardado en Entity
    public EmployeeResponse saveEmpResponse(@RequestBody EmployeeRequest employeeRequest){
        return  employeeService.saveEmployee(employeeRequest);
    }

    @PutMapping("/updateReqToEntity/{id}")
    public EmployeeResponse updateEmpResponse(@RequestBody EmployeeRequest employeeRequest, @PathVariable("id") Long id){
        return  employeeService.updateEmployee(employeeRequest, id);
    }
}
