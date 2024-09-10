package com.example.registrationservice.service;

import com.example.registrationservice.model.Registration;
import com.example.registrationservice.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Base URLs for the external services
    private final String userServiceUrl = "http://localhost:7001/api"; // User Service URL
    private final String tripServiceUrl = "http://localhost:7002/api"; // Trip Service URL

    public Registration registerUser(String username, Long tripId) {
        // Fetch user details using RestTemplate
        Map<String, Object> user = fetchUserDetails(username);
        if (user == null || user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // Fetch trip details using RestTemplate
        Map<String, Object> trip = fetchTripDetails(tripId);
        if (trip == null || trip.isEmpty()) {
            throw new RuntimeException("Trip not found");
        }

        // Check trip size limit
        int registrationCount = registrationRepository.countByTripId(tripId);
        Integer tripSize = (Integer) trip.get("size");
        if (registrationCount >= tripSize) {
            throw new RuntimeException("Trip is full");
        }

        // Check gender and age restrictions
        if (!isEligibleForTrip(user, trip)) {
            throw new RuntimeException("User does not meet the trip's requirements");
        }

        // Register the user for the trip
        Registration registration = new Registration();
        registration.setUsername(username);
        registration.setTripId(tripId);

        return registrationRepository.save(registration);
    }

    public void unregisterUser(String username, Long tripId) {
        Optional<Registration> registration = registrationRepository.findByUsernameAndTripId(username, tripId);
        if (registration.isPresent()) {
            registrationRepository.delete(registration.get());
        } else {
            throw new RuntimeException("Registration not found");
        }
    }

    private boolean isEligibleForTrip(Map<String, Object> user, Map<String, Object> trip) {
        // Extract user details from the Map
        String userGender = (String) user.get("gender");
        Integer userAge = (Integer) user.get("age");

        // Extract trip details from the Map
        String tripGenderRestriction = (String) trip.get("genderRestriction");
        Integer minAge = (Integer) trip.get("minAge");
        Integer maxAge = (Integer) trip.get("maxAge");

        // Check gender restrictions
        if (!tripGenderRestriction.equalsIgnoreCase("mixed") &&
                !tripGenderRestriction.equalsIgnoreCase(userGender)) {
            System.out.println("Gender restriction does not match.");
            return false;
        }

        // Check age restrictions
        if (userAge < minAge || userAge > maxAge) {
            System.out.println("Age restriction does not match.");
            return false;
        }
        return true;
    }

    private Map<String, Object> fetchUserDetails(String username) {
        URI uri = UriComponentsBuilder.fromUriString(userServiceUrl + "/users/{username}")
                .buildAndExpand(username)
                .toUri();

        try {
            // Fetch user details as a Map
            return restTemplate.getForObject(uri, Map.class);
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> fetchTripDetails(Long tripId) {
        URI uri = UriComponentsBuilder.fromUriString(tripServiceUrl + "/trips/{id}")
                .buildAndExpand(tripId)
                .toUri();

        try {
            // Fetch trip details as a Map
            return restTemplate.getForObject(uri, Map.class);
        } catch (Exception e) {
            return null;
        }
    }
}
