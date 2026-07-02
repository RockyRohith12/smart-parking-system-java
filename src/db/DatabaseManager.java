package db;

import java.sql.*;

public class DatabaseManager {
    // update these with your values
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smart_parking?serverTimezone=UTC";
    private static final String DB_USER = "smart_user"; // <-- change if needed
    private static final String DB_PASS = "your_password"; // <-- change to your password

    static {
        try {
            // Load driver (optional for modern drivers, but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Put the connector JAR in lib/ and add to classpath.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    // small helper to test connection
    public static boolean testConnection() {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT 1");
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1) == 1;
            return false;
        } catch (SQLException e) {
            System.err.println("DB connection test failed: " + e.getMessage());
            return false;
        }
    }
}
