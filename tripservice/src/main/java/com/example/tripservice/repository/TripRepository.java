package com.example.tripservice.repository;

import com.example.tripservice.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    // Method to search for trips based on destination, dates, and size
    List<Trip> findByDestinationContainingAndDatesContainingAndSizeLessThanEqual(
            String destination, String dates, Integer size);

    // Additional methods for specific queries can be added here if needed
}
