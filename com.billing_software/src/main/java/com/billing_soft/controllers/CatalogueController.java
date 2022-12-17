package com.billing_soft.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billing_soft.dtos.Cataloguedto;
import com.billing_soft.entities.Catalogue;
import com.billing_soft.mappers.CatalogueMapper;
import com.billing_soft.services.CatalogueService;



@Controller
public class CatalogueController {
	
	@Autowired
	private CatalogueService catalogueService;
	
	@Autowired
	private CatalogueMapper catalogueMapper;
	
	

	@RequestMapping("/catalogue")
	public String Cataloguepage(Model model, @ModelAttribute("catalog")  Catalogue catalog )
	{
		List<Catalogue> product =this.catalogueService.getAll();
		
		List<Cataloguedto> products= new ArrayList<>();
		
		products=catalogueMapper.cataloguedtolist(product);
		
		System.out.println(products);
		
		model.addAttribute("products", products);
		
		return "catalogue";
	}
	
	@PostMapping("/catalogueprocess")
	public String formprocess(@ModelAttribute("catalog")  Catalogue catalog)
	{
		
		this.catalogueService.create(catalog);
		
		return "redirect:/catalogue";
	}
	
	@RequestMapping("/catalogue/del/{id}")
	public String deletingrow(@PathVariable Integer id)
	{
		
		this.catalogueService.delbyid(id);
		
		return "redirect:/catalogue";
	}
	
	@RequestMapping("/catalogue/update/{id}")
	public String updatingrow(@PathVariable Integer id , Model model)
	{
		List<Catalogue> product =this.catalogueService.getAll();
		
		List<Cataloguedto> products= new ArrayList<>();
		
		products=catalogueMapper.cataloguedtolist(product);
		
		System.out.println(products);
		
		model.addAttribute("products", products);
		
		Catalogue catalogue = this.catalogueService.getbyid(id);
		
		System.out.println(catalogue);
		
		model.addAttribute("catalog", catalogue);
		
		return "catalogue";
		
	}
	
	

}
