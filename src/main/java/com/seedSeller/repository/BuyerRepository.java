package com.seedSeller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seedSeller.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	@Query("select b from Buyer b where b.phone_number=:number")
	Buyer getUserByNumber(String number);
	
	
	Buyer findByUsername(String username);

	
//	Buyer findByPhone_number(String username);



}
