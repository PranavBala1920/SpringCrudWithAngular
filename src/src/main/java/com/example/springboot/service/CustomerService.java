package com.example.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.springboot.entity.CustomerModel;
import com.example.springboot.repository.CustomerRepository;

public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public List<CustomerModel> findbyEmailList(String email){
		List<CustomerModel> emaiIDList = customerRepository.findEmaiList(email); 
		return emaiIDList;
	}

}