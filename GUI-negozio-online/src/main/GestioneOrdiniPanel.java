import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GestioneOrdiniPanel extends JPanel {

    private JTextField txtNomeCliente;
    private JTextField txtQuantita;
    private JTable tblProdotti;
    private JTable tblProdottiOrdine;
    private DefaultTableModel prodottiTableModel;
    private DefaultTableModel ordineTableModel;
    private JLabel lblTotaleOrdine;
    private SimulazioneDatabase database;
    private Ordine ordineInCorso;

    public GestioneOrdiniPanel(SimulazioneDatabase database) {
        this.database = database;
        setLayout(new BorderLayout());

        // Inizializza l'ordine in corso
        ordineInCorso = new Ordine(1, "", new HashMap<>());

        // Pannello superiore per input
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(new JLabel("Nome Cliente:"));
        txtNomeCliente = new JTextField();
        inputPanel.add(txtNomeCliente);

        inputPanel.add(new JLabel("Quantità:"));
        txtQuantita = new JTextField();
        inputPanel.add(txtQuantita);

        JButton btnAggiungiProdotto = new JButton("Aggiungi Prodotto al Carrello");
        inputPanel.add(btnAggiungiProdotto);

        add(inputPanel, BorderLayout.NORTH);

        // Tabella per mostrare i prodotti disponibili
        prodottiTableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Prezzo", "Quantità"}, 0);
        tblProdotti = new JTable(prodottiTableModel);
        JScrollPane prodottiScrollPane = new JScrollPane(tblProdotti);
        add(prodottiScrollPane, BorderLayout.WEST);

        // Tabella per mostrare i prodotti nell'ordine
        ordineTableModel = new DefaultTableModel(new String[]{"Prodotto", "Quantità", "Prezzo Unità", "Totale Prodotto"}, 0);
        tblProdottiOrdine = new JTable(ordineTableModel);
        JScrollPane ordineScrollPane = new JScrollPane(tblProdottiOrdine);
        add(ordineScrollPane, BorderLayout.CENTER);

        // Etichetta per il totale dell'ordine
        JPanel totalPanel = new JPanel(new BorderLayout());
        lblTotaleOrdine = new JLabel("Totale: 0.0");
        totalPanel.add(lblTotaleOrdine, BorderLayout.CENTER);
        add(totalPanel, BorderLayout.SOUTH);

        // Listener per il pulsante di aggiunta prodotto
        btnAggiungiProdotto.addActionListener(e -> {
            try {
                int selectedRow = tblProdotti.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Seleziona un prodotto.");
                    return;
                }

                int idProdotto = (int) prodottiTableModel.getValueAt(selectedRow, 0);
                Prodotto prodotto = database.leggiProdotto(idProdotto);

                int quantita = Integer.parseInt(txtQuantita.getText().trim());

                if (quantita > prodotto.getQuantitaDisponibile()) {
                    JOptionPane.showMessageDialog(this, "Quantità non disponibile.");
                    return;
                }

                ordineInCorso.aggiungiProdotto(prodotto, quantita);

                aggiornaTabellaOrdine();

                lblTotaleOrdine.setText("Totale: " + ordineInCorso.getTotaleOrdine());

                prodotto.decrementaQuantita(quantita);
                database.aggiornaProdotto(prodotto);

                aggiornaTabellaProdotti();

                txtQuantita.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantità non valida.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Errore nell'aggiungere il prodotto: " + ex.getMessage());
            }
        });

        aggiornaTabellaProdotti();
    }

    public void aggiornaTabellaProdotti() {
        prodottiTableModel.setRowCount(0);
        for (Prodotto prodotto : database.getProdotti().values()) {
            prodottiTableModel.addRow(new Object[]{
                    prodotto.getId(),
                    prodotto.getNome(),
                    prodotto.getPrezzo(),
                    prodotto.getQuantitaDisponibile()
            });
        }
    }

    private void aggiornaTabellaOrdine() {
        ordineTableModel.setRowCount(0);
        for (Map.Entry<Prodotto, Integer> entry : ordineInCorso.getProdotti().entrySet()) {
            ordineTableModel.addRow(new Object[]{
                    entry.getKey().getNome(),
                    entry.getValue(),
                    entry.getKey().getPrezzo(),
                    entry.getKey().getPrezzo() * entry.getValue()
            });
        }
    }
}
