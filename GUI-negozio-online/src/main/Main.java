import javax.swing.*;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creazione del database simulato
        SimulazioneDatabase database = new SimulazioneDatabase();

        // Creazione del pannello GestioneOrdiniPanel
        GestioneOrdiniPanel gestioneOrdiniPanel = new GestioneOrdiniPanel(database);

        // Creazione del pannello GestioneProdottiPanel
        GestioneProdottiPanel gestioneProdottiPanel = new GestioneProdottiPanel(database, gestioneOrdiniPanel);

        Prodotto p1 = new Prodotto(1, "Prodotto 1", 10.0, 5);
        Prodotto p2 = new Prodotto(2, "Prodotto 2", 20.0, 3);
        database.aggiungiProdotto(p1);
        database.aggiungiProdotto(p2);

        Ordine ordine = new Ordine(1, "Cliente 1", new HashMap<>());
        ordine.aggiungiProdotto(p1, 2); // Aggiungi 2 pezzi di Prodotto 1
        ordine.aggiungiProdotto(p2, 1); // Aggiungi 1 pezzo di Prodotto 2
        // Creazione della finestra principale
        JFrame frame = new JFrame("Gestione Negozio Online");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Aggiunta dei pannelli a un TabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gestione Ordini", gestioneOrdiniPanel);
        tabbedPane.addTab("Gestione Prodotti", gestioneProdottiPanel);

        frame.add(tabbedPane);

        // Mostra la finestra
        frame.setVisible(true);

    }
}
