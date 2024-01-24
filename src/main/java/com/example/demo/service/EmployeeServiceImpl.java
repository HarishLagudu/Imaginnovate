package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployeeById(String employeeId) {
		return employeeRepository.findById(employeeId);
	}
}
