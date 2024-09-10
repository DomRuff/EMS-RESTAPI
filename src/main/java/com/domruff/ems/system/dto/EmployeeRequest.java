package com.domruff.ems.system.dto;

import com.domruff.ems.system.model.EmployeeRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String firstName;
    private String lastName;
    private String email;
    private EmployeeRole role;

}
