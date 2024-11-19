package com.example.tripservice.controller;

import com.example.tripservice.model.Trip;
import com.example.tripservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getTripById(@PathVariable Long id) {
        try {
            Optional<Trip> tripOptional = tripService.getTripById(id);

            if (tripOptional.isPresent()) {
                return ResponseEntity.ok(tripOptional.get()); // Returns ResponseEntity<Trip>
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Trip with ID: " + id + " not found."); // Returns ResponseEntity<String>
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching trip with ID: " + id + ". " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        Trip createdTrip = tripService.createTrip(trip);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrip);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTrip(@PathVariable Long id, @RequestBody Trip tripDetails) {
        try {
            Trip updatedTrip = tripService.updateTrip(id, tripDetails);
            return ResponseEntity.ok(updatedTrip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trip with ID: " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long id) {
        try {
            tripService.deleteTrip(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Trip with ID: " + id + " not found.");
        }
    }
    @GetMapping("/updated-since")
    public ResponseEntity<List<Trip>> getUpdatedTrips(@RequestParam String timestamp) {
        try {
            LocalDateTime updatedSince = LocalDateTime.parse(timestamp); // Parse the timestamp
            List<Trip> updatedTrips = tripService.findTripsUpdatedSince(updatedSince);
            return ResponseEntity.ok(updatedTrips);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Return 400 for invalid timestamp
        }
    }
}
