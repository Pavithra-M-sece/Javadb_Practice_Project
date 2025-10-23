package com.example.blood.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    @JsonIgnoreProperties({"donations"})
    private Donor donor;

    @ManyToOne
    @JoinColumn(name = "blood_drive_id")
    @JsonIgnoreProperties({"donations"})
    private BloodDrive bloodDrive;

    private LocalDate date;  // ✅ LocalDate for proper date handling

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public BloodDrive getBloodDrive() { return bloodDrive; }
    public void setBloodDrive(BloodDrive bloodDrive) { this.bloodDrive = bloodDrive; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
