package com.billing_soft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Product_List")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalogue {
	
	@Id
	@Column(name = "Product_ID")
	private Integer Pid;
	
	@Column(name = "Product_Name")
	private String p_name ;
	
	@Column(name = "Product_Category")
	private String p_category;
	
	
	private Double price;
	

}
