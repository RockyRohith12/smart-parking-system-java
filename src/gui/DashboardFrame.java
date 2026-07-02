package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import model.Vehicle;
import service.ParkingService;
import java.sql.*;

// Multithreading (correct package)
import service.RevenueMonitor;

public class DashboardFrame extends JFrame {

    private ParkingService parkingService;

    // Background multithreading
    private RevenueMonitor monitor;
    private Thread monitorThread;

    public DashboardFrame(ParkingService ps) {
        this.parkingService = ps;

        setTitle("Smart Parking Dashboard");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnEnter = new JButton("Enter Vehicle");
        JButton btnExit = new JButton("Exit Vehicle");
        JButton btnView = new JButton("View Parked Vehicles");
        JButton btnRevenue = new JButton("Daily Revenue");
        JButton btnLogout = new JButton("Logout");

        add(btnEnter);
        add(btnExit);
        add(btnView);
        add(btnRevenue);
        add(btnLogout);

        // ------------------------------
        // BUTTON ACTIONS
        // ------------------------------

        btnEnter.addActionListener(e ->
                new EnterVehicleFrame(parkingService).setVisible(true));

        btnExit.addActionListener(e ->
                new ExitVehicleFrame(parkingService).setVisible(true));

        btnView.addActionListener(e -> {
            Map<String, Vehicle> parked = parkingService.getAllParkedVehicles();
            if (parked.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No vehicles parked currently.");
            } else {
                StringBuilder sb = new StringBuilder();
                parked.forEach((reg, vehicle) ->
                        sb.append(reg).append(" - ").append(vehicle.getType()).append("\n")
                );
                JOptionPane.showMessageDialog(this, sb.toString(),
                        "Parked Vehicles", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnRevenue.addActionListener(e -> {
            try (Connection conn = db.DBConnection.getConnection()) {

                String sql = "SELECT SUM(amount) FROM receipts WHERE DATE(paid_at) = CURDATE()";
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                double total = 0;
                if (rs.next()) total = rs.getDouble(1);

                JOptionPane.showMessageDialog(this, "Today's Revenue: ₹" + total);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            }
        });

        // ------------------------------
        // START BACKGROUND THREAD
        // ------------------------------
        monitor = new RevenueMonitor();
        monitorThread = new Thread(monitor);
        monitorThread.start();

        // ------------------------------
        // UPDATED LOGOUT
        // ------------------------------
        btnLogout.addActionListener(e -> {
            monitor.stopMonitor(); // stop thread safely
            dispose();
            new LoginFrame().setVisible(true);
        });
    }
}
