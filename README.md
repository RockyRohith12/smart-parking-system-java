# 🚗 Smart Parking Management System

A desktop-based **Smart Parking Management System** developed using **Java Swing**, **JDBC**, and **MySQL**. The application automates parking operations by managing vehicle entry, exit, ticket generation, billing, parking slot allocation, and revenue monitoring through an intuitive graphical interface.

---

## ✨ Features

- 🔐 Secure Login System
- 🚘 Vehicle Entry Management
- 🚪 Vehicle Exit Management
- 🎫 Automatic Parking Ticket Generation
- 💰 Automated Billing System
- 📊 Revenue Monitoring
- 🅿️ Parking Slot Management
- 🗄️ MySQL Database Integration
- ⚠️ Custom Exception Handling
- 🖥️ Java Swing GUI

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java | Core Programming |
| Java Swing | Desktop GUI Development |
| JDBC | Database Connectivity |
| MySQL | Database Management |
| OOP | Application Design |

---

## 📂 Project Structure

```text
SmartParking
│
├── lib
│   └── mysql-connector-j-9.5.0.jar
│
├── src
│   ├── db
│   ├── exceptions
│   ├── gui
│   ├── model
│   ├── service
│   └── ui
│
├── .gitignore
└── README.md
```

---

## 🧩 Modules

### Authentication
- Secure user login

### Parking Management
- Vehicle Entry
- Vehicle Exit
- Parking Slot Allocation
- Parking Availability Monitoring

### Billing
- Automatic Fee Calculation
- Bill Generation
- Revenue Tracking

### Database
- Vehicle Records
- Parking Tickets
- Billing History
- Parking Logs

---

## 💡 Object-Oriented Concepts

- Classes & Objects
- Inheritance
- Polymorphism
- Encapsulation
- Interfaces
- Exception Handling

---

## ⚙️ Prerequisites

Before running the project, ensure you have:

- Java JDK 17 or later
- MySQL Server
- MySQL Connector/J
- Any Java IDE (VS Code, IntelliJ IDEA, Eclipse)

---

## 🚀 Getting Started

### Clone the repository

```bash
git clone https://github.com/RockyRohith12/smart-parking-system-java.git
```

### Open the project

Open the project in your preferred Java IDE.

### Configure the database

Update your database credentials in:

```
src/db/DBConnection.java
```

Example:

```java
String url = "jdbc:mysql://localhost:3306/parking_db";
String username = "root";
String password = "your_password";
```

### Add MySQL Connector

Ensure the following library is added to the project's build path:

```
lib/mysql-connector-j-9.5.0.jar
```

### Run the application

Run either:

```
App.java
```

or

```
ParkingApp.java
```

---

## 🚀 Future Enhancements

- QR Code Based Entry
- RFID Integration
- Online Payment Gateway
- Mobile Application
- Parking Reservation
- Email Notifications
- Admin Analytics Dashboard

---

## 👨‍💻 Author
**Anushka Acharya**

B.Tech - Computer Science & Engineering

**V. Rohith**

B.Tech – Computer Science & Engineering (AI & ML)

Dayananda Sagar University

GitHub: https://github.com/Anu5156

GitHub: https://github.com/RockyRohith12
---

## 📄 License

This project is intended for educational and learning purposes.