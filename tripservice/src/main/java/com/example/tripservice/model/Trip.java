package com.example.tripservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String startingPoint;
    private LocalDate startDate;
    private LocalDate endDate;
    private int size; // Maximum group size
    private String genderRestrictions; // Male, Female, Unisex, etc.
    private String ageRestrictions; // Age range (e.g., 18-30)
    private double estimatedCost;
    private String details;

    public String getStartingPoint() {
        return startingPoint;
    }

    public int getSize() {
        return size;
    }

    public String getAgeRestrictions() {
        return ageRestrictions;
    }

    public void setAgeRestrictions(String ageRestrictions) {
        this.ageRestrictions = ageRestrictions;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(double estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getGenderRestrictions() {
        return genderRestrictions;
    }

    public void setGenderRestrictions(String genderRestrictions) {
        this.genderRestrictions = genderRestrictions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
    // Getters and Setters
}
