package com.billing_soft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing_soft.entities.Catalogue;
import com.billing_soft.repositories.CatalogueRepository;

@Service
public class CatalogueService {
	
	
	@Autowired
	private CatalogueRepository catalogueRepository;
	
	
	public Catalogue create(Catalogue catalogue)
	{
		Catalogue cat = catalogueRepository.save(catalogue);
		return cat;
	}
	
	public Catalogue getbyid(Integer id)
	{
		Optional<Catalogue> optional= catalogueRepository.findById(id);
		Catalogue catalogue = optional.get();
		return catalogue;
	}
	
	public void delbyid(Integer id)
	{
		catalogueRepository.deleteById(id);
		
	}
	
	public List<Catalogue> getAll()
	{
		List<Catalogue> catalogues = catalogueRepository.findAll();
		return catalogues;
	}
	
	

}
