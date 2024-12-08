package com.seedSeller.service;

import java.time.LocalDate;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seedSeller.model.Buyer;
import com.seedSeller.model.Purchase;
import com.seedSeller.model.Seed;
import com.seedSeller.repository.BuyerRepository;
import com.seedSeller.repository.PurchaseRepository;
import com.seedSeller.repository.SeedRespository;

import DataTransferObject.DataTransfer;

@Service
public class BuyerService {

	@Autowired
	private BuyerRepository buyerRepo;
	@Autowired
	private SeedRespository seedRespo;
	@Autowired
	private PurchaseRepository purchaseRepo;
	@Autowired
	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public boolean setBuyerData(Buyer buyer) {
		boolean check=false;
		boolean isThere=buyerRepo.count()==0;
		if(isThere) {
			buyer.setRole("admin");
//			buyer.setPassword(encoder.encode(buyer.getPassword()));
			buyerRepo.save(buyer);
			check=true;
		}else {
			buyer.setRole("user");
			buyerRepo.save(buyer);
			check=true;
		}
		
		if(check) {
			return check;
		}
		return check;
	}
	
	public void buy(DataTransfer purchase) {
		
		int userId=2;
		LocalDate data=LocalDate.now();
		Purchase p=new Purchase();
		
		Optional<Buyer> buyer=buyerRepo.findById(userId);
		Buyer b=null;
		if(buyer.isPresent()) {
			System.out.println(b);
			b=buyer.get();
		}
		Optional<Seed> seed=seedRespo.findById(purchase.getId());
		Seed s=null;
		if(seed.isPresent()) {
			s=seed.get();
		}
		p.setPurchaseDate(data);
		p.setBuyer(b);
		p.setQuantity(purchase.getQuantity());
		p.setTotalPrice(purchase.getPrice());
		p.setSeed(s);
		p.setLocation(purchase.getLocation());
		p.setStatus("open");
		purchaseRepo.save(p);
	}

	public String verify(Buyer buyer) {
		
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(buyer.getUsername(), buyer.getPassword()));
		if(authentication.isAuthenticated())
			return jwtService.generateToken(buyer.getUsername());
		return "failed";
	}
}
