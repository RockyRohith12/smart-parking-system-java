package service;

import db.DBConnection;
import exceptions.InvalidVehicleException;
import exceptions.ParkingFullException;
import model.Billable;
import model.ParkingTicket;
import model.Vehicle;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ParkingService {

    private ParkingLot parkingLot;
    private BillingService billingService;
    private Map<String, ParkingTicket> activeTickets;

    public ParkingService(int capacity) {
        this.parkingLot = new ParkingLot(capacity);
        this.billingService = new BillingService();
        this.activeTickets = new HashMap<>();
    }

    // ----------------------------------------
    // ENTER VEHICLE
    // ----------------------------------------
    public void enterVehicle(Vehicle vehicle) throws ParkingFullException, SQLException {

        parkingLot.enterVehicle(vehicle);

        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement psVehicle = conn.prepareStatement(
                    "INSERT IGNORE INTO vehicles (reg_no, vehicle_type) VALUES (?, ?)");
            psVehicle.setString(1, vehicle.getRegNo());
            psVehicle.setString(2, vehicle.getType().name());
            psVehicle.executeUpdate();

            LocalDateTime now = LocalDateTime.now();

            PreparedStatement psTicket = conn.prepareStatement(
                    "INSERT INTO tickets (reg_no, vehicle_type, entry_time, paid) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            psTicket.setString(1, vehicle.getRegNo());
            psTicket.setString(2, vehicle.getType().name());
            psTicket.setTimestamp(3, Timestamp.valueOf(now));
            psTicket.setBoolean(4, false);

            psTicket.executeUpdate();

            ResultSet rs = psTicket.getGeneratedKeys();
            int ticketId = 0;
            if (rs.next()) ticketId = rs.getInt(1);

            ParkingTicket ticket = new ParkingTicket(vehicle.getRegNo(), vehicle.getType(), now);
            ticket.setTicketId(ticketId);

            activeTickets.put(vehicle.getRegNo(), ticket);
        }
    }

    // ----------------------------------------
    // EXIT VEHICLE
    // ----------------------------------------
    public double exitVehicle(String regNo) throws InvalidVehicleException, SQLException {

        Vehicle vehicle = parkingLot.getParkedVehicle(regNo);
        if (vehicle == null)
            throw new InvalidVehicleException("Vehicle not found!");

        parkingLot.exitVehicle(regNo);

        ParkingTicket ticket = activeTickets.get(regNo);
        if (ticket == null)
            throw new InvalidVehicleException("Ticket not found!");

        LocalDateTime exitTime = LocalDateTime.now();
        ticket.setExitTime(exitTime);

        // ------------------------
        // NEW INTERFACE BILLING LOGIC
        // ------------------------
        long minutes = Math.max(1, Duration.between(ticket.getEntryTime(), exitTime).toMinutes());

        double amount;

        if (vehicle instanceof Billable) {
            amount = ((Billable) vehicle).calculateCharge(minutes);
        } else {
            long hours = Math.max(1, Duration.between(ticket.getEntryTime(), exitTime).toHours());
            amount = billingService.calculateBill(vehicle, hours);
        }

        ticket.setAmount(amount);
        ticket.setPaid(true);

        // ----------------------------------------
        // UPDATE DATABASE
        // ----------------------------------------
        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement psTicket = conn.prepareStatement(
                    "UPDATE tickets SET exit_time=?, paid=?, amount=? WHERE ticket_id=?");

            psTicket.setTimestamp(1, Timestamp.valueOf(exitTime));
            psTicket.setBoolean(2, true);
            psTicket.setDouble(3, amount);
            psTicket.setInt(4, ticket.getTicketId());
            psTicket.executeUpdate();

            PreparedStatement psReceipt = conn.prepareStatement(
                    "INSERT INTO receipts (ticket_id, reg_no, amount, paid_at, details) VALUES (?, ?, ?, ?, ?)");

            psReceipt.setInt(1, ticket.getTicketId());
            psReceipt.setString(2, ticket.getRegNo());
            psReceipt.setDouble(3, amount);
            psReceipt.setTimestamp(4, Timestamp.valueOf(exitTime));
            psReceipt.setString(5, "Paid in cash/card");

            psReceipt.executeUpdate();
        }

        activeTickets.remove(regNo);
        return amount;
    }

    // ----------------------------------------
    // AVAILABLE SLOTS
    // ----------------------------------------
    public int getAvailableSlots() {
        return parkingLot.getAvailableSlots();
    }

    // ----------------------------------------
    // PRINT PARKED VEHICLES
    // ----------------------------------------
    public void printAllParkedVehicles() {
        Map<String, Vehicle> all = parkingLot.getAllParkedVehicles();
        if (all.isEmpty()) {
            System.out.println("No vehicles parked.");
            return;
        }

        System.out.println("\n--- Parked Vehicles ---");
        all.forEach((reg, vehicle) ->
                System.out.println(reg + " (" + vehicle.getType() + ")"));
    }

    // ----------------------------------------
    // GET ALL PARKED VEHICLES FOR GUI
    // ----------------------------------------
    public Map<String, Vehicle> getAllParkedVehicles() {
        return parkingLot.getAllParkedVehicles();
    }
}
