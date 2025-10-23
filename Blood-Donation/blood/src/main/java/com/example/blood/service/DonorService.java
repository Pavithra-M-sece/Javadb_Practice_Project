package com.example.blood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blood.entity.Donor;
import com.example.blood.repository.DonorRepository;

@Service
public class DonorService {
    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) { this.donorRepository = donorRepository; }

    public List<Donor> getAllDonors() { return donorRepository.findAll(); }
    public Optional<Donor> getDonorById(Long id) { return donorRepository.findById(id); }
    public Donor saveDonor(Donor donor) { return donorRepository.save(donor); }
    public void deleteDonor(Long id) { donorRepository.deleteById(id); }
}
