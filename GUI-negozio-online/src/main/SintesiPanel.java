import javax.swing.*;
import java.awt.*;

public class SintesiPanel extends JPanel {

    private JLabel lblNumeroOrdini;
    private JLabel lblTotaleIncassi;
    private JLabel lblQuantitaTotaleMagazzino;

    private SimulazioneDatabase database;

    public SintesiPanel(SimulazioneDatabase database) {
        this.database = database;
        setLayout(new GridLayout(3, 2, 10, 10));

        // Numero totale di ordini
        add(new JLabel("Numero totale di ordini:"));
        lblNumeroOrdini = new JLabel("0");
        add(lblNumeroOrdini);

        // Totale incassi generati
        add(new JLabel("Totale incassi generati:"));
        lblTotaleIncassi = new JLabel("0.00 €");
        add(lblTotaleIncassi);

        // Quantità totale disponibile in magazzino
        add(new JLabel("Quantità totale in magazzino:"));
        lblQuantitaTotaleMagazzino = new JLabel("0");
        add(lblQuantitaTotaleMagazzino);

        aggiornaSintesi();
    }

    public void aggiornaSintesi() {
        // Calcola il numero totale di ordini
        int numeroOrdini = database.getOrdini().size();
        lblNumeroOrdini.setText(String.valueOf(numeroOrdini));

        // Calcola il totale degli incassi
        double totaleIncassi = database.getOrdini().values().stream()
                .mapToDouble(Ordine::getTotaleOrdine)
                .sum();
        lblTotaleIncassi.setText(String.format("%.2f €", totaleIncassi));

        // Calcola la quantità totale disponibile in magazzino
        int quantitaTotale = database.getProdotti().values().stream()
                .mapToInt(Prodotto::getQuantitaDisponibile)
                .sum();
        lblQuantitaTotaleMagazzino.setText(String.valueOf(quantitaTotale));
    }
}
