package com.seedSeller.controller;



import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedSeller.model.Buyer;

import com.seedSeller.service.BuyerService;

import DataTransferObject.BuyerDataTransfer;
import DataTransferObject.DataTransfer;

@RestController
@RequestMapping("/api")
public class BuyerContoller {
	
	@Autowired
	private BuyerService buyerService;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/buyerData")
	public void getBuyer(@RequestBody Buyer buyer) {
		LocalDate currentData=LocalDate.now();
		buyerService.setBuyerData(buyer);
		System.out.println(buyer);
	}
	
	@PostMapping("/setBuyerData")
	public boolean setBuyerData(@RequestBody BuyerDataTransfer buyer) {
		System.out.println(buyer);
		Buyer buyer2=new Buyer();
		buyer2.setPassword(encoder.encode(buyer.getPassword()) );
		buyer2.setPhone_number(buyer.getPhone_number());
		buyer2.setUsername(buyer.getUsername());
		return buyerService.setBuyerData(buyer2);
	}
	@PostMapping("/buySeed")
	public void buySeed(@RequestBody DataTransfer purchase) {
		System.out.println(purchase);
		buyerService.buy(purchase);

	}

	@PostMapping("/abc")
	public String login(@RequestBody BuyerDataTransfer buyerDataTransfer) {
		System.out.println(buyerDataTransfer);
		Buyer buyer=new Buyer();
		buyer.setPassword(buyerDataTransfer.getPassword());
		buyer.setUsername(buyerDataTransfer.getPhone_number());
		return buyerService.verify(buyer);
	}
	@GetMapping("/check")
	public String checking() {
		return "hello everyone";
	}
}
