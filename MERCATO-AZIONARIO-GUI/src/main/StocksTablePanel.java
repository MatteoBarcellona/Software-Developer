import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StocksTablePanel extends JPanel {
    private JTable stocksTable;

    public StocksTablePanel(List<Stock> stocks) {
        setLayout(new BorderLayout());

        String[] columns = {"Nome Azienda", "Prezzo Corrente", "Storico Prezzi"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        for (Stock stock : stocks) {
            String[] row = {
                    stock.getName(),
                    String.valueOf(stock.getCurrentPrice()),
                    stock.getPriceHistory().toString()
            };
            tableModel.addRow(row);
        }

        stocksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(stocksTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
