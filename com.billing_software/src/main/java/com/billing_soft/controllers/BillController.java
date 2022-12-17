package com.billing_soft.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.billing_soft.entities.Bill_temp;
import com.billing_soft.entities.Inventory;
import com.billing_soft.repositories.BillTempRepository;
import com.billing_soft.services.BillTempService;
import com.billing_soft.services.InventoryService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class BillController {

	@Autowired
	private BillTempService billTempService;
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private BillTempRepository billTempRepository;

	@RequestMapping("/billing")
	public String inventoryview(Model model) {
		
		List<Bill_temp> Items = billTempService.getAll();
		model.addAttribute("Items", Items);
		System.out.println(Items);
		
		Integer total = billTempRepository.findSumOfPrices();
		System.out.println(total);
		model.addAttribute("total", total);
		return "Billing";
	}

	@PostMapping("/billingprocess")
	public String billingprocess(@ModelAttribute Bill_temp bill_temp) {
	Inventory inventory=inventoryService.getItembyPid(bill_temp.getPid());
	if(bill_temp.getQuantity()<=inventory.getQuantity())
	{
	bill_temp.setP_name(inventory.getCatalogue().getP_name());
	bill_temp.setPrice(bill_temp.getQuantity()*inventory.getCatalogue().getPrice());
	
	}
	else
	{
		throw new RuntimeException();
	}
	this.billTempService.create(bill_temp);
	
	return "redirect:/billing";
	}
	
	
	@RequestMapping("/billing/del/{id}")
	public String deleteRow(@PathVariable Integer id )
	{
		billTempService.delbyid(id);
		
		
		return "Billing";
	}
	
	
	@PostMapping("/billingupdateprocess")
	public String billingupdateprocess(@ModelAttribute Bill_temp bill_temp )
	{
		System.out.println(bill_temp);
		billTempService.update(bill_temp, bill_temp.getPid());
		return "redirect:/billing";
	}
	
	@RequestMapping("/deleteAll")
	public String deleteAllRows()
	{
		billTempService.delAll();
		return "redirect:/billing";
	}
	@RequestMapping("/print")
	public ResponseEntity pdf() throws FileNotFoundException, JRException
	{
		Integer total = billTempRepository.findSumOfPrices();
		
		return billTempService.exportReport(Integer.toString(total));
	}
	
}
