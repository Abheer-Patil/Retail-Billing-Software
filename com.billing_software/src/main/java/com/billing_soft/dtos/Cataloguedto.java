package com.billing_soft.dtos;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Cataloguedto {
	

	
	private Integer Pid;
	
	
	private String p_name ;
	
	
	private String p_category;
	
	
	private Double price;

}
