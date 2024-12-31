import java.util.HashMap;
import java.util.Map;

public class User {
    private String email;
    private double balance;
    private String password;
    private Map<String, Integer> ownedStocks;

    public User(String email, String password, double balance) {
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.ownedStocks = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public Map<String, Integer> getOwnedStocks() {
        return ownedStocks;
    }

    public String getPassword() {
        return password;
    }

    public void updateBalance(double amount) {
        this.balance += amount;
    }

    public void addStock(String stockName, int quantity) {
        ownedStocks.put(stockName, ownedStocks.getOrDefault(stockName, 0) + quantity);
    }

    public void removeStock(String stockName, int quantity) {
        if (ownedStocks.containsKey(stockName)) {
            int currentQuantity = ownedStocks.get(stockName);
            if (currentQuantity >= quantity) {
                ownedStocks.put(stockName, currentQuantity - quantity);
            }
        }
    }


}
