package ui;

import java.util.Scanner;
import model.*;
import service.*;
import exceptions.*;

public class ParkingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // SET PARKING CAPACITY
        ParkingService parkingService = new ParkingService(50);

        while (true) {
            System.out.println("\n===== SMART PARKING SYSTEM =====");
            System.out.println("1. Enter Vehicle");
            System.out.println("2. Exit Vehicle");
            System.out.println("3. View Available Slots");
            System.out.println("4. View Parked Vehicles");
            System.out.println("5. Exit Program");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter vehicle number: ");
                    String regNo = sc.nextLine();

                    System.out.print("Enter vehicle type (CAR / BIKE / TRUCK): ");
                    String typeStr = sc.nextLine().toUpperCase();

                    VehicleType type;
                    try {
                        type = VehicleType.valueOf(typeStr);
                    } catch (Exception e) {
                        System.out.println("Invalid type!");
                        break;
                    }

                    Vehicle vehicle = switch (type) {
                        case CAR -> new Car(regNo);
                        case BIKE -> new Bike(regNo);
                        case TRUCK -> new Truck(regNo);
                    };

                    try {
                        parkingService.enterVehicle(vehicle);
                        System.out.println("Vehicle successfully entered!");
                    } catch (ParkingFullException e) {
                        System.out.println("Parking Full!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter vehicle number to exit: ");
                    String exitReg = sc.nextLine();

                    try {
                        double bill = parkingService.exitVehicle(exitReg);
                        System.out.println("Vehicle exited.");
                        System.out.println("Bill Amount = Rs." + bill);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Available Slots: " + parkingService.getAvailableSlots());
                    break;

                case 4:
                    parkingService.printAllParkedVehicles();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
