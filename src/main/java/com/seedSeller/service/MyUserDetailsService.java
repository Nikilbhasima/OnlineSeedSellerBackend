package com.seedSeller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.seedSeller.model.Buyer;
import com.seedSeller.repository.BuyerRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private BuyerRepository buyerRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Buyer buyer=buyerRepo.getUserByNumber(username);
		if(buyer==null) {
			throw new UsernameNotFoundException("user is not in database");
		}
		return new MyUserDetails(buyer);
	}

}
