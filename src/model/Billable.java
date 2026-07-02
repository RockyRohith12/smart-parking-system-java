package model;

/**
 * Interface that all vehicle types implement to calculate charges.
 */
public interface Billable {
    /**
     * Calculate charge for a duration in minutes.
     * @param durationMinutes duration parked in minutes
     * @return amount to charge
     */
    double calculateCharge(long durationMinutes);
}
