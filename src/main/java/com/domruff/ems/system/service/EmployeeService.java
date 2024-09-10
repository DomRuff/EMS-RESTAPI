package com.domruff.ems.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.domruff.ems.system.dto.EmployeeRequest;
import com.domruff.ems.system.dto.EmployeeResponse;
import com.domruff.ems.system.exception.ResourceNotFoundException;
import com.domruff.ems.system.mapper.EmployeeMapper;
import com.domruff.ems.system.model.Employee;
import com.domruff.ems.system.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Create Employee
    public void createEmployee(EmployeeRequest employeeRequest) {

        Employee employee = EmployeeMapper.toEmployee(employeeRequest);
        employeeRepository.save(employee);
    }

    // Get All Employees
    public List<EmployeeResponse> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();

        return employees.stream()
                .map((employee) -> EmployeeMapper.toEmployeeResponse(employee)).toList();
    }

    // Get Employee by ID
    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return EmployeeMapper.toEmployeeResponse(employee);
    }

    // Update Employee
    public void updateEmployee(Long id, EmployeeRequest employeeRequest) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emplyee not found"));

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setRole(employeeRequest.getRole());

        employeeRepository.save(employee);
    }

    // Delete Employee
    public void deleteEmployee(Long id) {

        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Employee not found");
        }
    }
}
