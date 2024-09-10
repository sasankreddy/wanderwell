**WanderWell: Trip and Vacation Management System**

WanderWell is a comprehensive trip and vacation management system designed to foster a vibrant community of travelers. This application allows users to discover, register for, and manage trips based on various preferences and criteria. It integrates seamlessly with other services to ensure a smooth and user-friendly experience.

**Features**

**Trip Discovery**: Browse and search for trips based on location, dates, group size, and other filters.

**User Registration**: Register for trips and manage user profiles, including age and gender.

**Trip Management**: Create, update, and delete trip postings with detailed information such as destination, starting point, dates, size, gender and age restrictions, estimated cost, and additional details.

**Community Engagement**: Engage with other users through features like finding a trip buddy.

**Microservices**

User Service: Handles user registration, authentication, and profile management. It stores user information and integrates with an authentication service for secure access.
Trip Service: Manages trip creation, updates, and deletions. It provides details on trips, including size, location, dates, and other specifications. Users can search for trips using various filters.
Registration Service: Manages user registrations for trips. It ensures that users meet trip-specific requirements and handles bookings and cancellations.

**Tech Stack**

Backend: Java with Spring Boot
Database: MySQL
RESTful Services: Spring Web, Spring Data JPA
Inter-Service Communication: Spring RestTemplate
Security: Basic Authentication

**Future Work**

We are working on integrating advanced features to enhance user experience. Future developments will include a chatbox for real-time communication among travelers and a recommendation system to suggest trips based on user preferences and history. These features aim to improve community interaction and provide personalized travel recommendations.

**Collaboration with Moto-Manager**

We are currently in the process of integrating WanderWell with the Moto-Manager application. This integration will facilitate seamless vehicle rental options for travelers, further enhancing the trip planning experience.
