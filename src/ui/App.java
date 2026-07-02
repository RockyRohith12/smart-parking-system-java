package ui;

import db.DatabaseManager;

public class App {
    public static void main(String[] args) {
        System.out.println("SmartParking - DB connection test");
        boolean ok = DatabaseManager.testConnection();
        if (ok) {
            System.out.println("✅ Successfully connected to the database.");
        } else {
            System.out.println("❌ Failed to connect. Check DB_URL, DB_USER, DB_PASS and connector JAR in lib/.");
        }
    }
}
