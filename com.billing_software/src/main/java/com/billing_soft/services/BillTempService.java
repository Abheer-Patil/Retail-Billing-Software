package com.billing_soft.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.billing_soft.entities.Bill_temp;
import com.billing_soft.entities.Catalogue;
import com.billing_soft.entities.Inventory;
import com.billing_soft.repositories.BillTempRepository;
import com.billing_soft.repositories.CatalogueRepository;
import com.billing_soft.repositories.InventoryRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class BillTempService {
	
	
	@Autowired
	private BillTempRepository billTempRepository;
	@Autowired
	private CatalogueRepository catalogueRepository;
	public Bill_temp create(Bill_temp bill_temp)
	{
		Bill_temp bt = billTempRepository.save(bill_temp);
		return bt;
	}
	
	public Bill_temp getbyid(Integer id)
	{
		Optional<Bill_temp> optional= billTempRepository.findById(id);
		Bill_temp bill_temp = optional.get();
		return bill_temp;
	}
	
	public void delbyid(Integer id)
	{
		billTempRepository.deleteById(id);
		
	}
	
	public List<Bill_temp> getAll()
	{
		List<Bill_temp> bill_temps = billTempRepository.findAll();
		return bill_temps;
	}
	
	
	public void delAll()
	{
		billTempRepository.deleteAll();
		
	}
	
	public Bill_temp update(Bill_temp bill_temp , Integer id)
	{
		Optional<Bill_temp> optional= billTempRepository.findById(id);
		Bill_temp bill = optional.get();
		Catalogue catalogue= catalogueRepository.findById(id).get();
		System.out.println("catalogue"+catalogue);
		bill.setQuantity(bill_temp.getQuantity());
		bill.setPrice(bill_temp.getQuantity()*catalogue.getPrice());
		billTempRepository.save(bill);
		return bill;
	}
	
	public ResponseEntity<byte[]> exportReport(String total) throws FileNotFoundException, JRException
	{
		List<Bill_temp> bill_temps = billTempRepository.findAll();
		System.out.println(bill_temps);
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(bill_temps);
		JasperReport compileReport =JasperCompileManager.compileReport(new FileInputStream("src/main/resources/bill.jrxml"));
		Map<String, Object> map = new HashMap<>();
		map.put("TOTAL_PRICE", total );
	JasperPrint report=	JasperFillManager.fillReport(compileReport,map, beanCollectionDataSource) ;
		//JasperExportManager.exportReportToPdfFile(report, "Invoice.pdf");
	
	byte[] data = JasperExportManager.exportReportToPdf(report);
		
	
	HttpHeaders headers = new HttpHeaders();
	headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=invoice.pdf");
	
	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	}

}
