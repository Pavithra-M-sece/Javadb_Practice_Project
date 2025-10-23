package com.example.blood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blood.entity.BloodDrive;
import com.example.blood.repository.BloodDriveRepository;

@Service
public class BloodDriveService {
    private final BloodDriveRepository bloodDriveRepository;

    public BloodDriveService(BloodDriveRepository bloodDriveRepository) { this.bloodDriveRepository = bloodDriveRepository; }

    public List<BloodDrive> getAllDrives() { return bloodDriveRepository.findAll(); }
    public Optional<BloodDrive> getDriveById(Long id) { return bloodDriveRepository.findById(id); }
    public BloodDrive saveDrive(BloodDrive bloodDrive) { return bloodDriveRepository.save(bloodDrive); }
    public void deleteDrive(Long id) { bloodDriveRepository.deleteById(id); }
}
