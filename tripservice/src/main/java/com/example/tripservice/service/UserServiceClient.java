package com.example.tripservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_URL = "http://localhost:7001/api/users/registered";

    public boolean isUserRegistered(String username) {
        String url = USER_SERVICE_URL + "?username=" + username;
        return restTemplate.getForObject(url, Boolean.class);
    }
}
