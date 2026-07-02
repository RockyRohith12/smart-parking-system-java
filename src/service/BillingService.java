package service;

import model.Vehicle;
import model.VehicleType;

public class BillingService {

    public double calculateBill(Vehicle vehicle, long hours) {

        double rate;

        switch (vehicle.getType()) {
            case CAR:
                rate = 20;
                break;
            case BIKE:
                rate = 10;
                break;
            case TRUCK:
                rate = 40;
                break;
            default:
                rate = 20;
        }

        return rate * hours;
    }
}
