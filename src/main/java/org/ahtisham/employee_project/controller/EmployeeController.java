package org.ahtisham.employee_project.controller;

import org.ahtisham.employee_project.model.Employee;
import org.ahtisham.employee_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
     EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("employee/{id}")
    public Employee getEmployeeByID(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("employee")
    public String addEmployee(@RequestBody Employee employee) {
     return employeeService.addEmployee(employee);
    }

    @DeleteMapping("employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if(employeeService.deleteEmployee(id))
            return "Employee deleted successfully";
        return "Not found";
    }

    @PutMapping("employee/{id}")
    public String updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
     return employeeService.updateEmployee(id, employee);
    }

}
