package com.example.blood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blood.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
}
