package com.billing_soft.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billing_soft.dtos.Inventoryresponsedto;
import com.billing_soft.entities.Catalogue;
import com.billing_soft.entities.Inventory;
import com.billing_soft.repositories.CatalogueRepository;
import com.billing_soft.repositories.InventoryRepository;

@Service
public class InventoryService {
	
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	
	public Inventory create(Inventory inventory)
	{
		List<Inventory> inventories = inventoryRepository.findAll();
		//Inventory inv = inventoryRepository.findInventoryByPid(inventory.getCatalogue().getPid()).get(0);
		Inventory inv1= inventory;
		for(Inventory i: inventories)
		{
			if(i.getCatalogue().getPid().equals(inventory.getCatalogue().getPid()))
			{
				
				i.setQuantity(i.getQuantity()+inventory.getQuantity());
				inv1=i;
			}
			
		}
		System.out.println(inv1);
		return inventoryRepository.save(inv1);
		}
	
	
	public Inventory getbyid(Integer id)
	{
		Optional<Inventory> optional= inventoryRepository.findById(id);
		Inventory inventory = optional.get();
		return inventory;
	}
	
	public void delbyid(Integer id)
	{
		inventoryRepository.deleteById(id);
		
	}
	
	public List<Inventory> getAll()
	{
		List<Inventory> inventories = inventoryRepository.findAll();
		return inventories;
	}
	
	public Catalogue getbypid(Integer id)
	{
		Catalogue catalogue= inventoryRepository.findByPid(id).get(0);
		
		return catalogue;
	}
	public void delbypid(Integer id)
	{
		Inventory inventory= inventoryRepository.findInventoryByPid(id).get(0);
		
		inventoryRepository.delete(inventory);
		
		
	}
	
	
	public List<Inventoryresponsedto> getallinventoryitems()
	{
		List<Inventory> inventories = inventoryRepository.findAll();
		List<Inventoryresponsedto> inventorydtos = new ArrayList<Inventoryresponsedto>();
		Inventoryresponsedto inventoryresponsedto=null;
		
		for(Inventory inventory: inventories)
		{
			inventoryresponsedto= new Inventoryresponsedto();
			inventoryresponsedto.setItemid(inventory.getItemid());
			inventoryresponsedto.setPid(inventory.getCatalogue().getPid());
			inventoryresponsedto.setP_name(inventory.getCatalogue().getP_name());
			inventoryresponsedto.setP_category(inventory.getCatalogue().getP_category());
			inventoryresponsedto.setQuantity(inventory.getQuantity());
			
			inventorydtos.add(inventoryresponsedto);
			
		}
		return inventorydtos;
	}

	public Inventory updateinventoryquantity( Inventory inventory , Integer Quantity)
	{
		Inventory inv = new Inventory();
		
		inv.setItemid(inventory.getItemid());
		inv.setQuantity(Quantity);
		inv.setCatalogue(inventory.getCatalogue());
		return this.inventoryRepository.save(inv);
	}
	
	public Inventory getItembyPid(Integer id)
	{
		List<Inventory> inventories = inventoryRepository.findAll();
		Inventory inv= null;
		for(Inventory inventory:inventories)
		{
			if(inventory.getCatalogue().getPid().equals(id))
			{
				inv=inventory;
			}
		}
		
		return inv;
	}

}
