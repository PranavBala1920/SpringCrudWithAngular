package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.CustomerModel;
import com.example.springboot.repository.CustomerRepository;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository repo;

	// get All data
	@GetMapping(path = "/", produces = "application/json")
	public List<CustomerModel> getAllData() {
		return repo.findAll();
	}

	// Save User
	@PostMapping(path = "/save", produces = "application/json")
	public CustomerModel addCustomer(@RequestBody CustomerModel customerModel) {
		return repo.save(customerModel);
	}

	// get customer details by ID
	@GetMapping("/{id}")
	public ResponseEntity<CustomerModel> getCustomerEntityId(@PathVariable Long id) {
		CustomerModel customerModel = repo.findById(id).orElseThrow();
		return ResponseEntity.ok(customerModel);
	}

	// Update User
	@PutMapping("/save/{id}")
	public ResponseEntity<CustomerModel> updateCustomer(@PathVariable Long id,
			@RequestBody CustomerModel customerdetails) {

		CustomerModel customerModel = repo.findById(id).orElseThrow();

		customerModel.setFirstname(customerdetails.getFirstname());
		customerModel.setLastname(customerdetails.getLastname());
		customerModel.setDateofbirth(customerdetails.getDateofbirth());
		customerModel.setAddress_one(customerdetails.getAddress_one());
		customerModel.setAddress_two(customerdetails.getAddress_two());
		customerModel.setAge(customerdetails.getAge());
		customerModel.setGender(customerdetails.getGender());
		customerModel.setEmail(customerdetails.getEmail());
	
		CustomerModel updatedCustomerModel = repo.save(customerModel);
		return ResponseEntity.ok(updatedCustomerModel);
	}

	// Delete User
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> delectCustomer(@PathVariable Long id) {
		CustomerModel customerModel = repo.findById(id).orElseThrow();
		repo.delete(customerModel);
		Map<String, Boolean> responce = new HashMap<>();
		responce.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(responce);
	}

	// Unique Email Id
	@GetMapping(path = "/email/{emailHeader}", produces = "application/json")
	public List<CustomerModel> uniqueEmail(@PathVariable String emailHeader) {
		List<CustomerModel> emailList = repo.findEmaiList(emailHeader);
		if (repo.findEmaiList(emailHeader) != null) {
			return null;
		} else {
			return emailList;
		}
	}
	
}



















