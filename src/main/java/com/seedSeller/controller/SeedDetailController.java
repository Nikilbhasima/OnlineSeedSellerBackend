package com.seedSeller.controller;


import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seedSeller.model.Seed;
import com.seedSeller.service.SeedService;

import DataTransferObject.SeedDataTransfer;

@RestController
@RequestMapping("/api")
public class SeedDetailController {
	
	@Autowired
	private SeedService seedService;

	@PostMapping("/seedData")
	public void seedData(@RequestBody SeedDataTransfer seed) {
		System.out.println(seed);
		Seed seed2=new Seed();
		seed2.setId(seed.getId());
		seed2.setDescription(seed.getDescription());
		seed2.setImg(Base64.getDecoder().decode(seed.getImg()));
		seed2.setName(seed.getName());
		seed2.setPrice(seed.getPrice());
		seed2.setQuantity(seed.getQuantity());
		seedService.setSeedData(seed2);
	}
	
	@GetMapping("/getAllData")
	public List<Seed> getAllData(){
		return seedService.getAllSeedData();
	}
	
	@DeleteMapping("/removeData/{id}")
	public void removeData(@PathVariable int id	) {
		seedService.deleteData(id);
	}
}
