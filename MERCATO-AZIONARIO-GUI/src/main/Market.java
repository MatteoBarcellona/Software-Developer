import java.util.ArrayList;
import java.util.List;

public class Market {
    private List<User> users;
    private List<Stock> stocks;

    public Market() {
        users = new ArrayList<>();
        stocks = new ArrayList<>();
        // Aggiungi qualche utente di esempio
        users.add(new User("user@example.com", "password123", 10000.00));
        users.add(new User("admin@example.com", "adminpass", 50000.00));
        // Aggiungi anche alcune azioni se necessario
        stocks.add(new Stock("Apple", 150.0));
        stocks.add(new Stock("Google", 2500.0));
        stocks.add(new Stock("Tesla", 700.0));
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public List<User> getUsers() {
        return users;
    }

    public void simulateMarket() {
        // Aggiorna casualmente il prezzo delle azioni
        for (Stock stock : stocks) {
            double newPrice = stock.getCurrentPrice() * (1 + (Math.random() - 0.5) / 10);
            stock.updatePrice(newPrice);
        }
    }

    public boolean isValidUser(String email, String password) {
        // Cerca l'utente con l'email fornita
        User user = users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);

        // Verifica che l'utente esista e che la password sia corretta
        return user != null && user.getPassword().equals(password);
    }
}
