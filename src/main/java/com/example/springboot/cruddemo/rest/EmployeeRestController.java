package com.example.springboot.cruddemo.rest;

import com.example.springboot.cruddemo.entity.Employee;
import com.example.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theemployeeService)
    {
        employeeService = theemployeeService;
    }

    @GetMapping("/employees/v1")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/v1/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        return employee;
    }

    @PostMapping("/employees/v1")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    }


    @PutMapping("/employees/v1")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        employeeService.save(theEmployee);

        return theEmployee;
    }


    @DeleteMapping("/employees/v1/{id}")
    public String deleteEmployee(@PathVariable int id) {

        Employee tempEmployee = employeeService.findById(id);


        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        employeeService.deleteById(id);

        return "Deleted employee id - " + id;
    }


}
