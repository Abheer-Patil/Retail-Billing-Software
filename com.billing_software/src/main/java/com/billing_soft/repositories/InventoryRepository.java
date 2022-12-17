package com.billing_soft.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.billing_soft.entities.Catalogue;
import com.billing_soft.entities.Inventory;


public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	
	@Query("select s from Catalogue s where s.Pid = :id")
	List<Catalogue> findByPid(@Param("id") Integer id);
	
	@Query("select s from Inventory s where s.catalogue = (select a from Catalogue a where a.Pid = :id)")
	List<Inventory> findInventoryByPid(@Param("id") Integer id);

}
