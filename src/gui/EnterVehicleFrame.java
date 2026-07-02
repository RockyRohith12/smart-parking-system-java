package gui;

import javax.swing.*;
import java.awt.*;
import model.*;
import service.ParkingService;
import exceptions.ParkingFullException;
import java.sql.SQLException;

public class EnterVehicleFrame extends JFrame {

    private ParkingService parkingService;

    public EnterVehicleFrame(ParkingService ps) {
        this.parkingService = ps;

        setTitle("Enter Vehicle");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblReg = new JLabel("Vehicle Number:");
        JTextField tfReg = new JTextField();
        JLabel lblType = new JLabel("Vehicle Type (CAR/BIKE/TRUCK):");
        JTextField tfType = new JTextField();

        JButton submitBtn = new JButton("Submit");
        JButton closeBtn = new JButton("Close");

        add(lblReg);
        add(tfReg);
        add(lblType);
        add(tfType);
        add(submitBtn);
        add(closeBtn);

        submitBtn.addActionListener(e -> {
            String regNo = tfReg.getText().trim();
            String typeStr = tfType.getText().trim().toUpperCase();

            VehicleType type;
            try {
                type = VehicleType.valueOf(typeStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Vehicle Type!");
                return;
            }

            Vehicle vehicle;
            if (type == VehicleType.CAR) vehicle = new Car(regNo);
            else if (type == VehicleType.BIKE) vehicle = new Bike(regNo);
            else vehicle = new Truck(regNo);

            try {
                parkingService.enterVehicle(vehicle);
                JOptionPane.showMessageDialog(this, "Vehicle Entered Successfully!");
            } catch (ParkingFullException ex) {
                JOptionPane.showMessageDialog(this, "Parking Full!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            }
        });

        closeBtn.addActionListener(e -> dispose());
    }
}
