package org.ahtisham.employee_project.service;

import org.ahtisham.employee_project.model.Employee;

import java.util.List;

public interface EmployeeService {
    String addEmployee(Employee employee);
    String updateEmployee(Long id,Employee employee);
    boolean deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getEmployees();
}
