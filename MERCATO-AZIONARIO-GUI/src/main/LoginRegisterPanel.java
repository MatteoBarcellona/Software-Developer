import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginRegisterPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    private Market market;

    public LoginRegisterPanel(Market market) {
        this.market = market;

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);
        loginButton.addActionListener(e -> handleLogin());

        registerButton = new JButton("Registrati");
        add(registerButton);
        registerButton.addActionListener(e -> handleRegister());
    }

    private void handleLogin() {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();

        User user = market.getUserByEmail(email);
        if (user != null) {
            // Passa al pannello principale dopo il login
            JOptionPane.showMessageDialog(this, "Benvenuto, " + user.getEmail());

            // Crea il pannello principale del mercato
            MarketPanel marketPanel = new MarketPanel(market, user);
            JFrame mainFrame = new JFrame("Mercato Azionario");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 600);
            mainFrame.add(marketPanel);
            mainFrame.setVisible(true);

            // Chiudi il pannello di login
            SwingUtilities.getWindowAncestor(this).dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Utente non trovato. Per favore, registrati.");
        }
    }


    private void handleRegister() {
        String email = emailField.getText();
        char[] password = passwordField.getPassword();

        if (email.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Email o password non validi.");
            return;
        }

        User newUser = new User(email, "password123",22222); // Saldo iniziale
        market.addUser(newUser);

        JOptionPane.showMessageDialog(this, "Registrazione avvenuta con successo.");
    }
}
