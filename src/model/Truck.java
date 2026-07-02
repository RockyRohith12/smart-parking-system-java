package model;

/**
 * Truck vehicle — implements Billable
 */
public class Truck extends Vehicle implements Billable {
    public Truck(String regNo) {
        super(regNo, VehicleType.TRUCK);
    }

    @Override
    public double calculateCharge(long durationMinutes) {
        double ratePerHour = 80.0; // ₹80 per hour
        double hours = durationMinutes / 60.0;
        return Math.ceil(hours) * ratePerHour;
    }
}
