package com.seedSeller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seedSeller.model.Seed;

public interface SeedRespository extends JpaRepository<Seed, Integer> {

}
