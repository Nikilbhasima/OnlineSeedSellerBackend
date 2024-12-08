package com.seedSeller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedSeller.model.Seed;
import com.seedSeller.repository.SeedRespository;

@Service
public class SeedService {

	@Autowired
	private SeedRespository seedRepo;
	
	public void setSeedData(Seed seed) {
		seedRepo.save(seed);
	}
	
	public List<Seed> getAllSeedData() {
		List<Seed> seeds=seedRepo.findAll();
		return seeds;
	}
	
	public void deleteData(int id) {
		seedRepo.deleteById(id);
	}
}
