package com.example.tripservice.service;

import com.example.tripservice.model.Trip;
import com.example.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(Long id, Trip tripDetails) {
        Optional<Trip> existingTrip = tripRepository.findById(id);
        if (existingTrip.isPresent()) {
            Trip trip = existingTrip.get();
            trip.setTripName(tripDetails.getTripName());
            trip.setTripSize(tripDetails.getTripSize());
            trip.setDestination(tripDetails.getDestination());
            trip.setDuration(tripDetails.getDuration());
            trip.setModeOfTransport(tripDetails.getModeOfTransport());
            trip.setAgeRequirement(tripDetails.getAgeRequirement());
            trip.setGenderRequirement(tripDetails.getGenderRequirement());
            trip.setStartDate(tripDetails.getStartDate());
            trip.setDescription(tripDetails.getDescription());
            trip.setHashtags(tripDetails.getHashtags());
            return tripRepository.save(trip);
        }
        throw new RuntimeException("Trip not found");
    }

    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
    public List<Trip> findTripsUpdatedSince(LocalDateTime updatedSince) {
        return tripRepository.findByUpdatedAtAfter(updatedSince);
    }

}
