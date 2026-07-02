package model;

/**
 * Base vehicle class — common fields and methods for all vehicles.
 */
public abstract class Vehicle {
    protected String regNo;
    protected VehicleType type;
    protected long entryEpochMillis; // store entry time as epoch millis

    public Vehicle(String regNo, VehicleType type) {
        this.regNo = regNo;
        this.type = type;
        this.entryEpochMillis = System.currentTimeMillis();
    }

    public String getRegNo() { return regNo; }
    public VehicleType getType() { return type; }
    public long getEntryEpochMillis() { return entryEpochMillis; }

    public void setEntryEpochMillis(long epochMillis) { this.entryEpochMillis = epochMillis; }

    @Override
    public String toString() {
        return type + " - " + regNo;
    }
}
