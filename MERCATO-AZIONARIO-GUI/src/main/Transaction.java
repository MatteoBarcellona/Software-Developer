public class Transaction {

    public static boolean buyStock(User user, Stock stock, int quantity) {
        double totalCost = stock.getCurrentPrice() * quantity;
        if (user.getBalance() >= totalCost) {
            user.updateBalance(-totalCost);
            user.addStock(stock.getName(), quantity);
            return true;
        }
        return false;
    }

    public static boolean sellStock(User user, Stock stock, int quantity) {
        if (user.getOwnedStocks().getOrDefault(stock.getName(), 0) >= quantity) {
            double totalIncome = stock.getCurrentPrice() * quantity;
            user.updateBalance(totalIncome);
            user.removeStock(stock.getName(), quantity);
            return true;
        }
        return false;
    }
}
