package com.billing_soft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billing_soft.entities.Catalogue;

public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {

}
