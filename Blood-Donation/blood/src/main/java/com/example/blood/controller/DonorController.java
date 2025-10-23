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

import com.example.blood.entity.Donor;
import com.example.blood.service.DonorService;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) { this.donorService = donorService; }

    @GetMapping
    public List<Donor> getAllDonors() { return donorService.getAllDonors(); }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        return donorService.getDonorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) { return donorService.saveDonor(donor); }

    @DeleteMapping("/{id}")
    public void deleteDonor(@PathVariable Long id) { donorService.deleteDonor(id); }
}
