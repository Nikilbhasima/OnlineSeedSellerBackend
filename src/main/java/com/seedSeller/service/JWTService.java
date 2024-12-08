package com.seedSeller.service;



import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.seedSeller.model.Buyer;
import com.seedSeller.repository.BuyerRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	@Autowired
	private BuyerRepository buyerRepo;
	private String secretKey="";
	
	public JWTService() {
		try {
			KeyGenerator KeyGen=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=KeyGen.generateKey();
			secretKey=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

	public String generateToken(String username) {
		Map<String, Object> claims=new HashMap<>();
		
		Buyer buyer=buyerRepo.getUserByNumber(username);
		claims.put("role", buyer.getRole());
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60*60*30*1000))
				.and()
				.signWith(getKey())
				.compact();
	}

	private Key getKey() {
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .verifyWith((SecretKey) getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }
    
	public boolean validateToken(String token, UserDetails userDetails) {
		  final String username = extractUsername(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	  private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	   
	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

}
