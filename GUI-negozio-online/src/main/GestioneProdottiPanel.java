import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

public class GestioneProdottiPanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtPrezzo;
    private JTextField txtQuantita;
    private JTable tblProdotti;
    private DefaultTableModel prodottiTableModel;

    private SimulazioneDatabase database;
    private GestioneOrdiniPanel gestioneOrdiniPanel;

    public GestioneProdottiPanel(SimulazioneDatabase database, GestioneOrdiniPanel gestioneOrdiniPanel) {
        this.database = database;
        this.gestioneOrdiniPanel = gestioneOrdiniPanel;

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        inputPanel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        inputPanel.add(txtNome);

        inputPanel.add(new JLabel("Prezzo:"));
        txtPrezzo = new JTextField();
        inputPanel.add(txtPrezzo);

        inputPanel.add(new JLabel("Quantità:"));
        txtQuantita = new JTextField();
        inputPanel.add(txtQuantita);

        JButton btnAggiungi = new JButton("Aggiungi Prodotto");
        JButton btnModifica = new JButton("Modifica Prodotto");
        JButton btnElimina = new JButton("Elimina Prodotto");

        buttonPanel.add(btnAggiungi);
        buttonPanel.add(btnModifica);
        buttonPanel.add(btnElimina);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        prodottiTableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Prezzo", "Quantità"}, 0);
        tblProdotti = new JTable(prodottiTableModel);
        JScrollPane scrollPane = new JScrollPane(tblProdotti);
        add(scrollPane, BorderLayout.CENTER);

        btnAggiungi.addActionListener(e -> aggiungiProdotto());

        btnModifica.addActionListener(e -> modificaProdotto());

        btnElimina.addActionListener(e -> eliminaProdotto());

        aggiornaTabellaProdotti();
    }

    private void aggiungiProdotto() {
        try {
            String nome = txtNome.getText().trim();
            double prezzo = Double.parseDouble(txtPrezzo.getText().trim());
            int quantita = Integer.parseInt(txtQuantita.getText().trim());

            int id = database.getProdotti().size() + 1;
            Prodotto prodotto = new Prodotto(id, nome, prezzo, quantita);
            database.aggiungiProdotto(prodotto);

            aggiornaTabellaProdotti();
            gestioneOrdiniPanel.aggiornaTabellaProdotti();

            pulisciCampi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore durante l'aggiunta: " + ex.getMessage());
        }
    }

    private void modificaProdotto() {
        int selectedRow = tblProdotti.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona un prodotto da modificare.");
            return;
        }

        try {
            int id = (int) prodottiTableModel.getValueAt(selectedRow, 0);
            String nuovoNome = txtNome.getText().trim();
            double nuovoPrezzo = Double.parseDouble(txtPrezzo.getText().trim());
            int nuovaQuantita = Integer.parseInt(txtQuantita.getText().trim());

            Optional<Prodotto> prodottoOpt = Optional.ofNullable(database.getProdotti().get(id));
            if (prodottoOpt.isPresent()) {
                Prodotto prodotto = prodottoOpt.get();
                prodotto.setNome(nuovoNome);
                prodotto.setPrezzo(nuovoPrezzo);
                prodotto.setQuantitaDisponibile(nuovaQuantita);

                aggiornaTabellaProdotti();
                gestioneOrdiniPanel.aggiornaTabellaProdotti();
                pulisciCampi();

                JOptionPane.showMessageDialog(this, "Prodotto modificato con successo.");
            } else {
                JOptionPane.showMessageDialog(this, "Prodotto non trovato.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore durante la modifica: " + ex.getMessage());
        }
    }

    private void eliminaProdotto() {
        int selectedRow = tblProdotti.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona un prodotto da eliminare.");
            return;
        }

        try {
            int id = (int) prodottiTableModel.getValueAt(selectedRow, 0);

            if (database.getProdotti().remove(id) != null) {
                aggiornaTabellaProdotti();
                gestioneOrdiniPanel.aggiornaTabellaProdotti();

                pulisciCampi();

                JOptionPane.showMessageDialog(this, "Prodotto eliminato con successo.");
            } else {
                JOptionPane.showMessageDialog(this, "Prodotto non trovato.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore durante l'eliminazione: " + ex.getMessage());
        }
    }

    private void aggiornaTabellaProdotti() {
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

    private void pulisciCampi() {
        txtNome.setText("");
        txtPrezzo.setText("");
        txtQuantita.setText("");
    }
}
