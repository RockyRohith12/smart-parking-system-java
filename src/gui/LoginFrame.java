package gui;

import javax.swing.*;

import service.ParkingService;

import java.awt.*;
import java.awt.event.*;
import ui.ParkingApp; // after login it will open the console menu (later we will switch to GUI dashboard)

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("Smart Parking - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Admin Login", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        form.add(userLabel);
        form.add(userField);
        form.add(passLabel);
        form.add(passField);
        form.add(new JLabel(""));
        form.add(loginBtn);

        add(form, BorderLayout.CENTER);

        // 🔐 Simple login logic
       loginBtn.addActionListener(e -> {
    String user = userField.getText();
    String pass = new String(passField.getPassword());

    if (user.equals("admin") && pass.equals("1234")) {
        JOptionPane.showMessageDialog(null, "Login Successful!");

        // Open Dashboard
        DashboardFrame dash = new DashboardFrame(new ParkingService(50));
        dash.setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Invalid Credentials!");
    }
});

    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
