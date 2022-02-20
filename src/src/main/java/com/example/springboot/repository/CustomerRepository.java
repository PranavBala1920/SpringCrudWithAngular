package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.CustomerModel;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {
	
	@Query(nativeQuery = true, value = "SELECT c.id FROM customers_details c WHERE c.email=:email, c.id <> id")
	public List<CustomerModel> findEmaiList(@Param("email") String email);
//	@Query(nativeQuery = true, value = "SELECT * FROM customers_details")
//	public List<CustomerModel> findEmaiList();
}
