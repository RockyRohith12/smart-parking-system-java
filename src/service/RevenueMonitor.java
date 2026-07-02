package service;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class RevenueMonitor implements Runnable {

    private boolean running = true;

    @Override
    public void run() {
        System.out.println("Revenue Monitor Thread Started...");

        while (running) {
            try {
                // Sleep for 60 seconds
                Thread.sleep(60000);

                double revenue = fetchTodayRevenue();
                System.out.println("AUTO-UPDATE >> Today's Revenue = ₹" + revenue);

            } catch (InterruptedException e) {
                System.out.println("Revenue Monitor Interrupted!");
                running = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Revenue Monitor Thread Stopped.");
    }

    // Function to fetch revenue from DB
    private double fetchTodayRevenue() {
        double total = 0;

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT SUM(amount) FROM receipts WHERE DATE(paid_at) = CURDATE()";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    // To stop thread safely
    public void stopMonitor() {
        running = false;
    }
}
