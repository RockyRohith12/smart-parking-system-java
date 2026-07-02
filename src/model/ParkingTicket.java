package model;

import java.time.LocalDateTime;

public class ParkingTicket {
    private int ticketId; // 0 if not persisted yet
    private String regNo;
    private VehicleType type;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private boolean paid;
    private double amount;

    public ParkingTicket(String regNo, VehicleType type, LocalDateTime entryTime) {
        this.regNo = regNo;
        this.type = type;
        this.entryTime = entryTime;
    }

    // getters & setters
    public int getTicketId() { return ticketId; }
    public void setTicketId(int ticketId) { this.ticketId = ticketId; }
    public String getRegNo() { return regNo; }
    public VehicleType getType() { return type; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }
    public boolean isPaid() { return paid; }
    public void setPaid(boolean paid) { this.paid = paid; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    @Override
    public String toString() {
        return "Ticket[" + ticketId + ", " + regNo + ", " + type + ", in=" + entryTime + ", out=" + exitTime + ", paid=" + paid + "]";
    }
}
