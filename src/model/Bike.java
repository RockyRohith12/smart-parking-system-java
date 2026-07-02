package model;

/**
 * Bike vehicle — implements Billable
 */
public class Bike extends Vehicle implements Billable {
    public Bike(String regNo) {
        super(regNo, VehicleType.BIKE);
    }

    @Override
    public double calculateCharge(long durationMinutes) {
        double ratePerHour = 20.0; // ₹20 per hour
        double hours = durationMinutes / 60.0;
        return Math.ceil(hours) * ratePerHour;
    }
}
