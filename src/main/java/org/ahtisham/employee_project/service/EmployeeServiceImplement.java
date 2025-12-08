package org.ahtisham.employee_project.service;

import org.ahtisham.employee_project.entity.EmployeeEntity;
import org.ahtisham.employee_project.model.Employee;
import org.ahtisham.employee_project.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImplement implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public String addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Employee added successfully";
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        var optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            return "Not found";
        }
        EmployeeEntity employeeEntity = optional.get();
        employeeEntity.setName(employee.getName());
        employeeRepository.save(employeeEntity);
        return "Updated Successfully";
    }

    @Override
    public boolean deleteEmployee(Long id) {
        var optional = employeeRepository.findById(id);
        if (optional.isEmpty()) {
            return false;
        }
        employeeRepository.delete(optional.get());
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        // 1. Use findById, but don't call .get() yet
        var entityOptional = employeeRepository.findById(id);

        // 2. Check if data exists to avoid the 500 Crash
        if (entityOptional.isPresent()) {
            EmployeeEntity employeeEntity = entityOptional.get();

            Employee employee = new Employee();

            // 3. Copy data (Requires Lombok @Data on 'Employee' class to work!)
            BeanUtils.copyProperties(employeeEntity, employee);

            return employee;
        }

        // 4. Return null (or throw an exception) if not found
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            Employee employee = new Employee();
            employee.setId(employeeEntity.getId());
            employee.setName(employeeEntity.getName());
            employees.add(employee);
        }
        return employees;
    }
}
