package com.seedSeller.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.seedSeller.model.Buyer;

public class MyUserDetails implements UserDetails {
	
	private Buyer buyer;
	
	public MyUserDetails(Buyer buyer) {
		this.buyer=buyer;
		System.out.println(buyer);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return buyer.getPassword();
	}

	@Override
	public String getUsername() {
		return buyer.getPhone_number();
	}

}
