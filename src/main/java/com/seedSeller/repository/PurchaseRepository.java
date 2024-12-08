package com.seedSeller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.seedSeller.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

	 @Query("SELECT p FROM Purchase p WHERE p.buyer.id = :id")
	List<Purchase> findByBuyer_Id(int id);

}
