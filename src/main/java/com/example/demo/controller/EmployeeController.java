package com.example.demo.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.NoSuchEmployeeExistsException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/getAllEmployess")
	public ResponseEntity<List<Employee>> getAllEmployess() {
		List<Employee> employee = new ArrayList<Employee>();
		try {
			employeeRepository.findAll().forEach(employee::add);
			if (employee.isEmpty()) {
				return new ResponseEntity<>(employee, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Double> getTaxDeductions(@PathVariable String id) {
		Optional<Employee> employee = Optional.ofNullable(employeeRepository.findById(id)
				.orElseThrow(() -> new NoSuchEmployeeExistsException("NO Employee PRESENT WITH ID = " + id)));
		if (employee.isPresent()) {
			Double d = getTaxDeduction(employee.get().getSalary(),employee.get().getDoj());
			return new ResponseEntity<>(d, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private static Double getTaxDeduction(Double income, Date date) {
		
		Format f= new SimpleDateFormat("yyyy-MM-dd");
		LocalDate start= LocalDate.parse(f.format(date));
		LocalDate last= LocalDate.parse("2025-03-31");
		long dif= ChronoUnit.DAYS.between(start, last);
		
		Double salinMonths=income/12;
		double salindays=salinMonths/30;
		
		Double actualIncome=dif*salindays;
		
		double tax = 0, chass = 0;
		double appIncome = 0;
		if (actualIncome <= 250000) {
			tax = 0;
		} else if (actualIncome >= 250001 && actualIncome <= 500000) {
			appIncome = actualIncome - 250000;
			tax = 0.05 * appIncome;
		} else if (actualIncome >= 500001 && actualIncome <= 1000000) {
			appIncome = actualIncome - 500000;
			tax = 12500 + (0.1 * appIncome);
		} else {
			appIncome = actualIncome - 1000000;
			tax = 62500 + (0.2 * appIncome);
		}
		if (actualIncome >= 2800000) {
			chass = (0.2 * tax);

		}
		return tax + chass;
	}

}
