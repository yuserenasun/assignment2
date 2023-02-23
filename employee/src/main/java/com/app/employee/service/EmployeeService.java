package com.app.employee.service;

import com.app.employee.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {
    private final List<Employee> employees;
    private final ObjectMapper objectMapper;

    public EmployeeService() {
        this.objectMapper = new ObjectMapper();
        try {
            // Read the JSON file and extract the "employee" array
            JsonNode rootNode = this.objectMapper.readTree(new File("src/main/resources/json/employee.json"));
            JsonNode employeesNode = rootNode.path("record").path("employee");

            // Convert the "employee" array to a List<Employee>
            employees = objectMapper.convertValue(employeesNode, new TypeReference<List<Employee>>(){});
        } catch (IOException e) {
            throw new RuntimeException("Error reading employee data from file", e);
        }
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}

