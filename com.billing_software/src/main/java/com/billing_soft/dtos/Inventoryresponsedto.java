package com.billing_soft.dtos;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Inventoryresponsedto {
	
	private Integer Itemid;
	
	
	private Integer Pid;
	
	
	private String p_name ;
	
	
	private String p_category;
	

	private Integer Quantity;
}
