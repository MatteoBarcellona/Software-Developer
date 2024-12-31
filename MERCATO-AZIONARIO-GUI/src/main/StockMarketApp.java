import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StockMarketApp {
    private static Market market;
    private static User currentUser;
    private static JFrame frame;
    private static JTextArea consoleArea;

    public static void main(String[] args) {
        market = new Market();
        currentUser = new User("user@example.com", "password123", 10000.00);
        market.addUser(currentUser);

        // Configura la finestra principale
        frame = new JFrame("Simulatore Mercato Azionario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Mostra la finestra di login all'avvio
        showLoginPanel();
    }

    private static void showLoginPanel() {
        // Crea il pannello di login con uno sfondo moderno
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setBackground(new Color(240, 240, 240));

        // Pannello superiore con un titolo
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 144, 255));
        JLabel titleLabel = new JLabel("Login - Mercato Azionario");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        // Pannello centrale per i campi di input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(passwordField, gbc);

        // Pannello inferiore con il pulsante di login
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 26));
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        buttonPanel.add(loginButton);

        // Aggiungi i pannelli alla finestra
        loginPanel.add(titlePanel, BorderLayout.NORTH);
        loginPanel.add(inputPanel, BorderLayout.CENTER);
        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(loginPanel);
        frame.setVisible(true);

        // Listener per il pulsante di login
        loginButton.addActionListener(e -> handleLogin(emailField.getText(), new String(passwordField.getPassword())));
    }

    private static void handleLogin(String email, String password) {
        if (market.isValidUser(email, password)) {
            currentUser = market.getUserByEmail(email);
            showMainPanel();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenziali non valide. Riprova.");
        }
    }

    private static void showMainPanel() {
        // Rimuovi il contenuto attuale e configura il pannello principale
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Pannello con i pulsanti laterali
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton viewStocksButton = new JButton("Visualizza azioni");
        JButton buyStockButton = new JButton("Acquista azioni");
        JButton sellStockButton = new JButton("Vendi azioni");
        JButton viewPortfolioButton = new JButton("Visualizza portafoglio");
        JButton simulateButton = new JButton("Simula Mercato");

        // Configura i pulsanti
        JButton[] buttons = {viewStocksButton, buyStockButton, sellStockButton, viewPortfolioButton, simulateButton};
        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setBackground(new Color(30, 144, 255));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            buttonPanel.add(button);
        }

        // Area di testo per la console
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(consoleArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Listener per i pulsanti
        viewStocksButton.addActionListener(e -> displayStocks());
        buyStockButton.addActionListener(e -> buyStock());
        sellStockButton.addActionListener(e -> sellStock());
        viewPortfolioButton.addActionListener(e -> viewPortfolio());
        simulateButton.addActionListener(e -> simulateMarket());

        frame.setContentPane(mainPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void displayStocks() {
        List<Stock> stocks = market.getStocks();
        StringBuilder sb = new StringBuilder("Azioni disponibili:\n");
        for (Stock stock : stocks) {
            sb.append(stock.getName()).append(" - Prezzo: ").append(stock.getCurrentPrice()).append("\n");
        }
        consoleArea.setText(sb.toString());
    }

    private static void buyStock() {
        String stockName = JOptionPane.showInputDialog(frame, "Nome dell'azione da acquistare:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Quante azioni vuoi acquistare?"));

        Stock stock = market.getStocks().stream()
                .filter(s -> s.getName().equalsIgnoreCase(stockName))
                .findFirst()
                .orElse(null);

        if (stock != null && Transaction.buyStock(currentUser, stock, quantity)) {
            consoleArea.setText("Acquisto completato! Saldo restante: " + currentUser.getBalance());
        } else {
            consoleArea.setText("Acquisto fallito. Controlla il saldo.");
        }
    }

    private static void sellStock() {
        String stockName = JOptionPane.showInputDialog(frame, "Nome dell'azione da vendere:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Quante azioni vuoi vendere?"));

        Stock stock = market.getStocks().stream()
                .filter(s -> s.getName().equalsIgnoreCase(stockName))
                .findFirst()
                .orElse(null);

        if (stock != null && Transaction.sellStock(currentUser, stock, quantity)) {
            consoleArea.setText("Vendita completata! Saldo attuale: " + currentUser.getBalance());
        } else {
            consoleArea.setText("Vendita fallita. Controlla le azioni possedute.");
        }
    }

    private static void viewPortfolio() {
        StringBuilder sb = new StringBuilder("Portafoglio dell'utente:\n");
        sb.append("Saldo: ").append(currentUser.getBalance()).append("\n");
        currentUser.getOwnedStocks().forEach((stock, quantity) -> {
            sb.append(stock).append(" - Quantità: ").append(quantity).append("\n");
        });
        consoleArea.setText(sb.toString());
    }

    private static void simulateMarket() {
        market.simulateMarket();
        consoleArea.setText("Prezzi aggiornati. Simulazione completata.");
    }
}
