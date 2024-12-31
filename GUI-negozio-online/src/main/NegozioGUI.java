import javax.swing.*;
import java.awt.*;

public class NegozioGUI {

    public static void main(String[] args) {
        // Crea l'oggetto SimulazioneDatabase
        SimulazioneDatabase database = new SimulazioneDatabase();

        // Crea il pannello GestioneOrdiniPanel
        GestioneOrdiniPanel gestioneOrdiniPanel = new GestioneOrdiniPanel(database);

        // Crea il pannello GestioneProdottiPanel, passando il database e il pannello ordini
        GestioneProdottiPanel gestioneProdottiPanel = new GestioneProdottiPanel(database, gestioneOrdiniPanel);

        // Crea il JFrame principale
        JFrame frame = new JFrame("Negozio Online");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Aggiungi i due pannelli al frame
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Gestione Ordini", gestioneOrdiniPanel);
        tabbedPane.addTab("Gestione Prodotti", gestioneProdottiPanel);

        frame.add(tabbedPane);

        // Mostra la finestra
        frame.setVisible(true);
    }
}
