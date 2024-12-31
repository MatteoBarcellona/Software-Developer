import javax.swing.*;
import java.awt.*;

public class PortfolioPanel extends JPanel {
    private JTextArea portfolioArea;

    public PortfolioPanel(User user) {
        setLayout(new BorderLayout());

        portfolioArea = new JTextArea(15, 30);
        portfolioArea.setEditable(false);
        add(new JScrollPane(portfolioArea), BorderLayout.CENTER);

        updatePortfolio(user);
    }

    public void updatePortfolio(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("Saldo: ").append(user.getBalance()).append("\n");
        sb.append("Azioni Possedute:\n");

        user.getOwnedStocks().forEach((name, quantity) -> {
            sb.append(name).append(": ").append(quantity).append("\n");
        });

        portfolioArea.setText(sb.toString());
    }
}
