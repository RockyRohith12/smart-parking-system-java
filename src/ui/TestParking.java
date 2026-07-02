package ui;

import model.Bike;
import model.Car;
import model.Truck;
import model.Vehicle;
import service.ParkingService;
import exceptions.ParkingFullException;
import exceptions.InvalidVehicleException;

import java.sql.SQLException;

public class TestParking {
public static void main(String[] args) {
ParkingService parkingService = new ParkingService(50);


    try {
        // Simulate vehicle entries
        Vehicle bike1 = new Bike("BIKE123");
        Vehicle car1 = new Car("CAR456");
        Vehicle truck1 = new Truck("TRUCK789");

        parkingService.enterVehicle(bike1);
        parkingService.enterVehicle(car1);
        parkingService.enterVehicle(truck1);

        System.out.println("Vehicles entered. Available slots: " + parkingService.getAvailableSlots());

        // Simulate exits
        double bikeBill = parkingService.exitVehicle("BIKE123");
        double carBill = parkingService.exitVehicle("CAR456");

        System.out.println("Bike bill: ₹" + bikeBill);
        System.out.println("Car bill: ₹" + carBill);
        System.out.println("Available slots after exit: " + parkingService.getAvailableSlots());

    } catch (ParkingFullException e) {
        System.out.println("Cannot enter vehicle: " + e.getMessage());
    } catch (InvalidVehicleException e) {
        System.out.println("Error on exit: " + e.getMessage());
    } catch (SQLException e) {
        System.out.println("Database error: " + e.getMessage());
    }
}


}
