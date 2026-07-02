package gui;

import javax.swing.*;
import service.ParkingService;
import exceptions.InvalidVehicleException;
import java.sql.SQLException;

public class ExitVehicleFrame extends JFrame {

    private ParkingService parkingService;

    public ExitVehicleFrame(ParkingService ps) {
        this.parkingService = ps;

        setTitle("Exit Vehicle");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lbl = new JLabel("Enter Vehicle Number:");
        lbl.setBounds(20, 20, 150, 25);
        add(lbl);

        JTextField txtReg = new JTextField();
        txtReg.setBounds(170, 20, 100, 25);
        add(txtReg);

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(100, 70, 80, 25);
        add(btnExit);

        btnExit.addActionListener(e -> {
            String regNo = txtReg.getText().trim();
            try {
                double bill = parkingService.exitVehicle(regNo);
                JOptionPane.showMessageDialog(this, "Vehicle exited. Total bill: ₹" + bill);
                dispose();
            } catch (InvalidVehicleException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        });
    }
}
