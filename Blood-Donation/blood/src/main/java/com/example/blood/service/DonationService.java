package com.example.blood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.blood.entity.BloodDrive;
import com.example.blood.entity.Donation;
import com.example.blood.entity.Donor;
import com.example.blood.repository.BloodDriveRepository;
import com.example.blood.repository.DonationRepository;
import com.example.blood.repository.DonorRepository;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;
    private final BloodDriveRepository bloodDriveRepository;

    public DonationService(DonationRepository donationRepository,
                           DonorRepository donorRepository,
                           BloodDriveRepository bloodDriveRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
        this.bloodDriveRepository = bloodDriveRepository;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Optional<Donation> getDonationById(Long id) {
        return donationRepository.findById(id);
    }

    // ✅ Updated logic: fetch full donor & drive before saving
    public Donation saveDonationWithRelations(Donation donation) {
        if (donation.getDonor() != null && donation.getDonor().getId() != null) {
            Donor donor = donorRepository.findById(donation.getDonor().getId()).orElse(null);
            donation.setDonor(donor);
        }

        if (donation.getBloodDrive() != null && donation.getBloodDrive().getId() != null) {
            BloodDrive drive = bloodDriveRepository.findById(donation.getBloodDrive().getId()).orElse(null);
            donation.setBloodDrive(drive);
        }

        return donationRepository.save(donation);
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }
}
