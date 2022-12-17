package com.billing_soft.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.billing_soft.dtos.Inventoryresponsedto;
import com.billing_soft.entities.Catalogue;
import com.billing_soft.entities.Inventory;
import com.billing_soft.services.CatalogueService;
import com.billing_soft.services.InventoryService;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private CatalogueService catalogueService;

	@RequestMapping("/inventory")
	public String inventoryview(@ModelAttribute Inventory inventory, Model model) {

		List<Inventoryresponsedto> list = new ArrayList<>();
		list = inventoryService.getallinventoryitems();
		model.addAttribute("Items", list);
		return "Inventory";
	}

	@PostMapping("/inventoryprocess")
	public String formprocess(@ModelAttribute Inventory inventory) {
		Catalogue catalogue = inventoryService.getbypid(inventory.getCatalogue().getPid());
		System.out.println(catalogue);
		inventory.setCatalogue(catalogue);
		inventoryService.create(inventory);

		return "redirect:/inventory";

	}

	@RequestMapping("/inventory/del/{id}")
	public String deletingrow(@PathVariable Integer id) {

		inventoryService.delbyid(id);

		return "redirect:/inventory";
	}

	@RequestMapping("/updateprocess")
	public String updatingrow(@ModelAttribute Inventory inventory  ) {
		
		
 	Inventory inv = this.inventoryService.getbyid(inventory.getItemid());
	System.out.println(inv);
	this.inventoryService.updateinventoryquantity(inv , inventory.getQuantity());
	
		
		return "redirect:/inventory";
		
		
	}

}
