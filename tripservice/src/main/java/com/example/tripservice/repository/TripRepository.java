package com.example.tripservice.repository;

import com.example.tripservice.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUpdatedAtAfter(LocalDateTime updatedAt);
}
