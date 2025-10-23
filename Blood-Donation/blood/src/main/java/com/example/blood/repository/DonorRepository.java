package com.example.blood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blood.entity.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
