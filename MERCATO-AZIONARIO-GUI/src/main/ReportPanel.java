import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {
    private JTextArea reportArea;

    public ReportPanel(Market market) {
        setLayout(new BorderLayout());

        reportArea = new JTextArea(10, 30);
        reportArea.setEditable(false);
        add(new JScrollPane(reportArea), BorderLayout.CENTER);

        generateReport(market);
    }

    public void generateReport(Market market) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report di Guadagni/Perdite:\n");

        double totalProfitLoss = 0;
        for (User user : market.getUsers()) {
            totalProfitLoss += user.getBalance();
        }

        sb.append("Profitto/Perdita Totale: ").append(totalProfitLoss).append("\n");

        sb.append("\nAzioni più popolari:\n");
        // Logica per azioni più popolari
        reportArea.setText(sb.toString());
    }
}
