package com.seedSeller.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedSeller.model.Purchase;
import com.seedSeller.service.PurchaseService;

import DataTransferObject.PurchasedData;

@RestController
@RequestMapping("/api")
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	
	@GetMapping("/getPurchaseData")
	public List<PurchasedData> getPurchaseData() {
		return purchaseService.getPurchaseData(2);
	}
	@DeleteMapping("/cancelOrder/{id}")
	public void cancelOrder(@PathVariable int id) {
		purchaseService.cancelOrder(id);
	}
	
	@PostMapping("/updatePurchase")
	public void updatePurchase(@RequestBody PurchasedData data) {
		System.out.println(data);
		purchaseService.updatePurchaseData(data);
	}
	@GetMapping("/getPurchaseDetail")
	public List<PurchasedData> getPurchaseDetail(){
		return purchaseService.getPurchaseData();
	}

	@PutMapping("/confirmOrder/{id}")
	public void confirmOrder(@PathVariable int id) {
		System.out.println(id);
		purchaseService.updateConfirm(id);
	}
}
