package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@NotNull(message = "Name may not be null")
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull(message = "Should have atleast one phone number")
	private List<String> phoneNumbers;

	@NotNull(message = "DOJ Should not be null")
	private Date doj;
	@NotNull
	private Double salary;
	
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	public Employee(String id, String firstName, String lastName, List<String> phoneNumbers, Date doj, Double salary,
			String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumbers = phoneNumbers;
		this.doj = doj;
		this.salary = salary;
		this.email = email;
	}
	public Employee() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
