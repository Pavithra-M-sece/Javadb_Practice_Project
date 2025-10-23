package com.example.blood.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blood.entity.BloodDrive;
import com.example.blood.service.BloodDriveService;

@RestController
@RequestMapping("/api/drives")
@CrossOrigin
public class BloodDriveController {

    private final BloodDriveService bloodDriveService;

    public BloodDriveController(BloodDriveService bloodDriveService) { this.bloodDriveService = bloodDriveService; }

    @GetMapping
    public List<BloodDrive> getAllDrives() { return bloodDriveService.getAllDrives(); }

    @GetMapping("/{id}")
    public ResponseEntity<BloodDrive> getDriveById(@PathVariable Long id) {
        return bloodDriveService.getDriveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BloodDrive createDrive(@RequestBody BloodDrive bloodDrive) { return bloodDriveService.saveDrive(bloodDrive); }

    @DeleteMapping("/{id}")
    public void deleteDrive(@PathVariable Long id) { bloodDriveService.deleteDrive(id); }
}
