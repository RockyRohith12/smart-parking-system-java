package service;

import model.Vehicle;
import exceptions.ParkingFullException;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int capacity;
    private Map<String, Vehicle> parkedVehicles;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedVehicles = new HashMap<>();
    }

    public synchronized void enterVehicle(Vehicle vehicle) throws ParkingFullException {
        if (parkedVehicles.size() >= capacity) {
            throw new ParkingFullException("Parking is full!");
        }
        parkedVehicles.put(vehicle.getRegNo(), vehicle);
    }

    public synchronized Vehicle getParkedVehicle(String regNo) {
        return parkedVehicles.get(regNo);
    }

    public synchronized void exitVehicle(String regNo) {
        parkedVehicles.remove(regNo);
    }

    public synchronized int getAvailableSlots() {
        return capacity - parkedVehicles.size();
    }

    public synchronized Map<String, Vehicle> getAllParkedVehicles() {
        return new HashMap<>(parkedVehicles);
    }
}
