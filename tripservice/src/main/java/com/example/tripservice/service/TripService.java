package com.example.tripservice.service;

import com.example.tripservice.model.Trip;
import com.example.tripservice.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserServiceClient userServiceClient; // Use RestTemplate for user verification

    public Trip createTrip(Trip trip, String username) {
        if (userServiceClient.isUserRegistered(username)) {
            return tripRepository.save(trip);
        } else {
            throw new RuntimeException("User is not registered");
        }
    }

    public List<Trip> searchTrips(String destination, String dates, Integer size) {
        return tripRepository.findByDestinationContainingAndDatesContainingAndSizeLessThanEqual(destination, dates, size);
    }

    public Trip updateTrip(Long id, Trip trip) {
        if (tripRepository.existsById(id)) {
            trip.setId(id);
            return tripRepository.save(trip);
        } else {
            throw new RuntimeException("Trip not found");
        }
    }

    public void deleteTrip(Long id) {
        if (tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
        } else {
            throw new RuntimeException("Trip not found");
        }
    }
}
