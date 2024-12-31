import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MarketPanel extends JPanel {
    private Market market;
    private User user;
    private JPanel stockButtonsPanel;

    public MarketPanel(Market market, User user) {
        this.market = market;
        this.user = user;

        setLayout(new BorderLayout());

        // Titolo del pannello
        JLabel titleLabel = new JLabel("Aziende disponibili", JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Pannello per i pulsanti delle azioni
        stockButtonsPanel = new JPanel();
        stockButtonsPanel.setLayout(new GridLayout(3, 5, 10, 10)); // 3 righe per le aziende, 5 per ogni azienda
        add(stockButtonsPanel, BorderLayout.CENTER);

        // Crea i pulsanti per ogni azienda
        for (Stock stock : market.getStocks()) {
            addStockButtons(stock);
        }

        // Mostra il portafoglio dell'utente
        showPortfolio();
    }

    private void addStockButtons(Stock stock) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Azienda " + stock.getName());
        JLabel priceLabel = new JLabel("Prezzo: " + stock.getCurrentPrice() + "€");

        JButton buyButton = new JButton("Acquista");
        buyButton.addActionListener(e -> handleBuy(stock));

        JButton sellButton = new JButton("Vendi");
        sellButton.addActionListener(e -> handleSell(stock));

        panel.add(nameLabel);
        panel.add(priceLabel);
        panel.add(buyButton);
        panel.add(sellButton);

        stockButtonsPanel.add(panel);
    }

    private void handleBuy(Stock stock) {
        String input = JOptionPane.showInputDialog(this, "Quante azioni vuoi acquistare di " + stock.getName() + "?");
        try {
            int quantity = Integer.parseInt(input);
            if (quantity > 0 && user.getBalance() >= stock.getCurrentPrice() * quantity) {
                user.addStock(stock.getName(), quantity);
                user.updateBalance(-stock.getCurrentPrice() * quantity);
                JOptionPane.showMessageDialog(this, "Hai acquistato " + quantity + " azioni di " + stock.getName());
                showPortfolio();
            } else {
                JOptionPane.showMessageDialog(this, "Saldo insufficiente o quantità non valida.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Inserisci un numero valido.");
        }
    }

    private void handleSell(Stock stock) {
        String input = JOptionPane.showInputDialog(this, "Quante azioni vuoi vendere di " + stock.getName() + "?");
        try {
            int quantity = Integer.parseInt(input);
            if (quantity > 0 && user.getOwnedStocks().getOrDefault(stock.getName(), 0) >= quantity) {
                user.removeStock(stock.getName(), quantity);
                user.updateBalance(stock.getCurrentPrice() * quantity);
                JOptionPane.showMessageDialog(this, "Hai venduto " + quantity + " azioni di " + stock.getName());
                showPortfolio();
            } else {
                JOptionPane.showMessageDialog(this, "Non possiedi abbastanza azioni da vendere.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Inserisci un numero valido.");
        }
    }

    private void showPortfolio() {
        // Aggiungi il portafoglio dell'utente (saldo e azioni possedute)
        StringBuilder portfolioText = new StringBuilder();
        portfolioText.append("Saldo: ").append(user.getBalance()).append("€\n");
        portfolioText.append("Azioni possedute:\n");
        for (String stockName : user.getOwnedStocks().keySet()) {
            portfolioText.append(stockName).append(": ").append(user.getOwnedStocks().get(stockName)).append(" azioni\n");
        }

        JOptionPane.showMessageDialog(this, portfolioText.toString());
    }
}
