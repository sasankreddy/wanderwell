package com.example.registrationservice.controller;

import com.example.registrationservice.model.Registration;
import com.example.registrationservice.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    /**
     * Registers a user for a specific trip.
     *
     * @param username the username of the user to register
     * @param tripId   the ID of the trip to register for
     * @return the registration details if successful, or an error response if not
     */
    @PostMapping
    public ResponseEntity<Registration> registerUser(
            @RequestParam String username,
            @RequestParam Long tripId) {
        try {
            Registration registration = registrationService.registerUser(username, tripId);
            return ResponseEntity.ok(registration);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Unregisters a user from a specific trip.
     *
     * @param username the username of the user to unregister
     * @param tripId   the ID of the trip to unregister from
     * @return a response indicating success or failure
     */
    @DeleteMapping
    public ResponseEntity<String> unregisterUser(
            @RequestParam String username,
            @RequestParam Long tripId) {
        try {
            registrationService.unregisterUser(username, tripId);
            return ResponseEntity.ok("User unregistered successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Failed to unregister user: " + e.getMessage());
        }
    }
}
