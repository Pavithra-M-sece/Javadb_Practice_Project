package com.example.blood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blood.entity.BloodDrive;

public interface BloodDriveRepository extends JpaRepository<BloodDrive, Long> {
}
