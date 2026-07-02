Smart Parking Management System 🚗

A desktop-based Smart Parking Management System developed using Java Swing, JDBC, and MySQL. The application automates vehicle parking operations including vehicle entry, exit, ticket generation, billing, and parking slot management.

Features
User Login
Vehicle Entry
Vehicle Exit
Parking Ticket Generation
Billing System
Revenue Monitoring
Parking Slot Management
Database Connectivity (MySQL)
Exception Handling
Java Swing GUI
Technologies Used
Technology	Purpose
Java	Core Programming
Java Swing	GUI Development
JDBC	Database Connectivity
MySQL	Database
OOP	System Design
Project Structure
src
│
├── db
│   ├── DBConnection.java
│   └── DatabaseManager.java
│
├── exceptions
│   ├── InvalidVehicleException.java
│   └── ParkingFullException.java
│
├── gui
│   ├── DashboardFrame.java
│   ├── EnterVehicleFrame.java
│   ├── ExitVehicleFrame.java
│   └── LoginFrame.java
│
├── model
│   ├── Vehicle.java
│   ├── Car.java
│   ├── Bike.java
│   ├── Truck.java
│   ├── ParkingTicket.java
│   ├── VehicleType.java
│   └── Billable.java
│
├── service
│   ├── ParkingLot.java
│   ├── ParkingService.java
│   ├── BillingService.java
│   └── RevenueMonitor.java
│
└── ui
    ├── App.java
    ├── ParkingApp.java
    ├── TestDB.java
    └── TestParking.java
Modules
Authentication

Secure login interface for accessing the application.

Parking Management
Vehicle Entry
Vehicle Exit
Slot Allocation
Capacity Monitoring
Billing
Automatic Parking Fee Calculation
Bill Generation
Revenue Tracking
Database

Stores

Vehicle Details
Parking Tickets
Billing Records
Parking History
Object-Oriented Concepts Used
Classes & Objects
Inheritance
Polymorphism
Interfaces
Exception Handling
Encapsulation
Screenshots

Add screenshots here.

images/
    login.png
    dashboard.png
    vehicle-entry.png
    vehicle-exit.png
Future Improvements
QR Code Based Entry
Online Payment
RFID Integration
Reservation System
Mobile Application
Email Notifications
Author

Anushka Acharya
Computer Science Engineering 

V. Rohith
Computer Science Engineering (AI & ML)

Dayananda Sagar University

License

This project is developed for educational purposes.