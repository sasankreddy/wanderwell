package com.example.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;
    private Integer age;
    private String gender;
    private String city;
    private String about;

    @Column(length = 1000)
    private String interests;

    @Column(name = "places_visited", length = 1000)
    private String placesVisited;
}
