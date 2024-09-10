package com.domruff.ems.system.mapper;

import com.domruff.ems.system.dto.EmployeeRequest;
import com.domruff.ems.system.dto.EmployeeResponse;
import com.domruff.ems.system.model.Employee;

public class EmployeeMapper {

    // Convert Employee to EmployeeResponse
    public static EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }

    // Convert EmployeeRequest to Employee
    public static Employee toEmployee(EmployeeRequest employeeRequest) {

        Employee employee = Employee.builder()
                .firstName(employeeRequest.getFirstName())
                .lastName(employeeRequest.getLastName())
                .email(employeeRequest.getEmail())
                .role(employeeRequest.getRole())
                .build();

        return employee;
    }
}
