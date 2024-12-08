package com.seedSeller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seedSeller.model.Purchase;
import com.seedSeller.model.Seed;
import com.seedSeller.repository.PurchaseRepository;
import com.seedSeller.repository.SeedRespository;

import DataTransferObject.PurchasedData;
import jakarta.transaction.Transactional;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepo;
	@Autowired
	private SeedRespository seedRespo;
	
	public void purchase(Purchase purchase) {
		purchaseRepo.save(purchase);
	}
	
	public List<PurchasedData> getPurchaseData(int id) {
		List<Purchase> purchaseData=purchaseRepo.findByBuyer_Id(id);
		
		List<PurchasedData> purchasedDatas=new ArrayList<>();
		for(Purchase d:purchaseData) {
			PurchasedData data=new PurchasedData();
			data.setId(d.getId());
			Optional<Seed> seed=seedRespo.findById(d.getSeed().getId());
			data.setName(seed.get().getName());
			data.setImg(seed.get().getImg());
			data.setLocation(d.getLocation());
			data.setPurchaseDate(d.getPurchaseDate());
			data.setQuantity(d.getQuantity());
			data.setTotalPrice(seed.get().getPrice());
			data.setStatus(d.getStatus());
			data.setUser_id(d.getBuyer().getId());
			data.setSeed_id(d.getSeed().getId());
			
			purchasedDatas.add(data);
		}
		return purchasedDatas;
		
	}
	
	public void cancelOrder(int id) {
		purchaseRepo.deleteById(id);
	}
	
	@Transactional
	public void updatePurchaseData(PurchasedData purchasedData) {

		Optional<Purchase> purchase=purchaseRepo.findById(purchasedData.getId());
		if(purchase.isPresent()) {
			System.out.println(purchase.get());
			Purchase p=purchase.get();
			p.setLocation(purchasedData.getLocation());
			p.setQuantity(purchasedData.getQuantity());
			purchaseRepo.save(p);
		}
	}
	
	public List<PurchasedData> getPurchaseData(){
		List<Purchase> purchaseData=purchaseRepo.findAll();
		List<PurchasedData> purchasedDatas=new ArrayList<>();
		for(Purchase d:purchaseData) {
			PurchasedData data=new PurchasedData();
			data.setId(d.getId());
			Optional<Seed> seed=seedRespo.findById(d.getSeed().getId());
			data.setName(seed.get().getName());
			data.setImg(seed.get().getImg());
			data.setLocation(d.getLocation());
			data.setPurchaseDate(d.getPurchaseDate());
			data.setQuantity(d.getQuantity());
			data.setTotalPrice(seed.get().getPrice());
			data.setStatus(d.getStatus());
			data.setUser_id(d.getBuyer().getId());
			data.setSeed_id(d.getSeed().getId());
			
			purchasedDatas.add(data);
		}
		return purchasedDatas;
	}
	
	public void updateConfirm(int id) {
		Optional<Purchase> purchase=purchaseRepo.findById(id);
		if(purchase.isPresent()) {
			Purchase p=purchase.get();
			p.setStatus("close");
			purchaseRepo.save(p);
		}
	}
}
