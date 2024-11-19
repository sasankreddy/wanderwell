package com.example.tripservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tripName;
    private Integer tripSize;
    private String destination;
    private Integer duration;
    private String modeOfTransport;
    private Integer ageRequirement;
    private String genderRequirement;
    private LocalDate startDate;
    private String description;
    @ElementCollection
    @CollectionTable(name = "trip_hashtags", joinColumns = @JoinColumn(name = "trip_id"))
    @Column(name = "hashtag")
    private Set<String> hashtags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}


