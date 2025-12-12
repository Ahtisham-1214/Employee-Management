package org.ahtisham.employee_project.controller;

import org.ahtisham.employee_project.model.Employee;
import org.ahtisham.employee_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
     EmployeeService employeeService;

    @GetMapping("employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping("employee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
     return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if(employeeService.deleteEmployee(id))
            return ResponseEntity.ok("Employee deleted successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
     String result = employeeService.updateEmployee(id, employee);
     if ("Not found".equalsIgnoreCase(result)) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
     }
     return ResponseEntity.ok(result);
    }

}
