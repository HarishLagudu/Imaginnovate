package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public Employee createEmployee(Employee employee);
	public Optional<Employee> getEmployeeById(String employeeId) ;
	
}
