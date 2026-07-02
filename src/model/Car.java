package model;

/**
 * Car vehicle — implements Billable
 */
public class Car extends Vehicle implements Billable {
    public Car(String regNo) {
        super(regNo, VehicleType.CAR);
    }

    @Override
    public double calculateCharge(long durationMinutes) {
        double ratePerHour = 40.0; // ₹40 per hour
        double hours = durationMinutes / 60.0;
        return Math.ceil(hours) * ratePerHour; // round up to next hour
    }
}
