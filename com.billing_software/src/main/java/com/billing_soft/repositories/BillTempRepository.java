package com.billing_soft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.billing_soft.entities.Bill_temp;

public interface BillTempRepository extends JpaRepository<Bill_temp, Integer> {

	@Query(value = "SELECT Sum(u.price) FROM Bill_temp u ")
	Integer findSumOfPrices();
}
