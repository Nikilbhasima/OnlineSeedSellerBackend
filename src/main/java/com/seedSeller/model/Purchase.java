package com.seedSeller.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "seed_id")
	@JsonBackReference
	@ToString.Exclude
	private Seed seed;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "buyer_id")
	@JsonBackReference
	@ToString.Exclude
	private Buyer buyer;

	private int quantity;
	private int totalPrice;
	private LocalDate purchaseDate;
	private String location;
	private String status;
}
