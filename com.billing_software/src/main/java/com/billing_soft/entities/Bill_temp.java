package com.billing_soft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "Bill")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill_temp {
	
	

	@Id
	@Column(name = "Product_ID")
	private Integer pid;
	
	@Column(name = "Product_Name")
	private String p_name ;
	
	private Integer quantity;
	
	private Double price;

}
