import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MusicPlayerGUI {
    private JFrame frame;
    private JTable canzoniTable;
    private JTable playlistTable;
    private GestoreMusica gestore;
    private DefaultTableModel canzoniTableModel;
    private DefaultTableModel playlistTableModel;

    public MusicPlayerGUI() {
        gestore = new GestoreMusica();
        initialize();
    }

    private JButton creaButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setBackground(new Color(21, 106, 21));  // Verde Lime
        button.setForeground(Color.WHITE);  // Testo bianco
        button.setFocusPainted(false);  // Rimuove il bordo di focus
        button.setPreferredSize(new Dimension(200, 40));  // Imposta una dimensione fissa
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(14, 158, 1));  // Verde più scuro
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(21, 106, 21));  // Verde originale
            }
        });
        return button;
    }


    private void setupTableAppearance(JTable table) {
        table.setBackground(new Color(30, 30, 30)); // Sfondo scuro
        table.setForeground(Color.WHITE);           // Testo bianco
        table.setSelectionBackground(new Color(60, 60, 60)); // Sfondo selezione
        table.setSelectionForeground(Color.WHITE);  // Testo selezione
        table.setGridColor(Color.WHITE);            // Linee bianche

        table.setFont(new Font("Arial", Font.PLAIN, 14)); // Font leggibile
        table.setRowHeight(25); // Altezza delle righe

        // Renderer personalizzato per le celle
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    cell.setBackground(new Color(70, 70, 70)); // Sfondo selezione
                } else {
                    cell.setBackground(new Color(30, 30, 30)); // Sfondo normale
                }
                setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Bordo bianco
                setHorizontalAlignment(SwingConstants.CENTER); // Testo centrato
                return cell;
            }
        });
    }



    private void setDarkTheme() {
        try {
            // Imposta il Look and Feel scuro di Nimbus
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Imposta colori scuri per la GUI
            UIManager.put("control", new Color(60, 63, 65));  // Colore di base della GUI
            UIManager.put("info", new Color(60, 63, 65));  // Colore delle finestre di info
            UIManager.put("nimbusBase", new Color(18, 30, 49));  // Colore base di Nimbus
            UIManager.put("nimbusFocus", new Color(115, 164, 209));  // Colore di focus
            UIManager.put("nimbusSelectionBackground", new Color(102, 102, 102));  // Colore selezione
            UIManager.put("nimbusSelectionForeground", Color.white);  // Colore testo selezionato

            // Colore di sfondo e del testo per la tabella
            UIManager.put("Table.background", Color.BLACK);  // Sfondo nero
            UIManager.put("Table.foreground", Color.WHITE);  // Testo bianco
            UIManager.put("Table.selectionBackground", new Color(70, 70, 70));  // Sfondo selezione grigio scuro
            UIManager.put("Table.selectionForeground", Color.WHITE);  // Testo bianco per selezione
            UIManager.put("Table.gridColor", new Color(80, 80, 80));  // Griglia grigio scuro

            // Colore dei bottoni
            UIManager.put("Button.background", new Color(21, 106, 21));  // Colore verde scuro
            UIManager.put("Button.foreground", Color.WHITE);  // Colore testo bianco
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void addTitle() {
        JLabel titleLabel = new JLabel("The Playlist GUI");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36)); // Font elegante e grande
        titleLabel.setForeground(new Color(34, 177, 76));        // Verde vivace
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrato

        // Aggiunta di un'ombra al testo con effetto HTML
        titleLabel.setText("<html><span style='text-shadow: 2px 2px 4px #000000;'>The Playlist GUI</span></html>");

        // Aggiungiamo un bordo per estetica
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Pannello per il titolo con sfondo scuro
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 30, 30)); // Sfondo scuro
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Aggiungiamo il pannello al frame
        frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
    }


    public void initialize() {
        setDarkTheme();
        frame = new JFrame("Gestore Playlist Musicali");
        frame.setBounds(100, 100, 1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        addTitle();
        // Pannello Canzoni
        JPanel canzoniPanel = new JPanel();
        tabbedPane.addTab("Canzoni", null, canzoniPanel, null);
        canzoniPanel.setLayout(new BorderLayout());
        String[] canzoniColumns = {"Titolo", "Artista", "Durata (s)", "Genere"};
        canzoniTableModel = new DefaultTableModel(canzoniColumns, 0);
        canzoniTable = new JTable(canzoniTableModel);
        setupTableAppearance(canzoniTable); // Applica lo stile alla tabella
        JScrollPane canzoniScrollPane = new JScrollPane(canzoniTable);
        canzoniPanel.add(canzoniScrollPane, BorderLayout.CENTER);
        canzoniTable.setBackground(Color.BLACK);
        canzoniTable.setForeground(Color.WHITE);
        canzoniTable.setSelectionBackground(new Color(70, 70, 70));
        canzoniTable.setSelectionForeground(Color.WHITE);

        canzoniPanel.add(canzoniScrollPane, BorderLayout.CENTER);

        JPanel canzoniControlPanel = new JPanel();
        canzoniPanel.add(canzoniControlPanel, BorderLayout.SOUTH);

        JButton aggiungiCanzoneButton = creaButton("Aggiungi Canzone");
        aggiungiCanzoneButton.addActionListener(e -> aggiungiCanzone());
        canzoniControlPanel.add(aggiungiCanzoneButton);

        JButton eliminaCanzoneButton = creaButton("Elimina Canzone");
        eliminaCanzoneButton.addActionListener(e -> eliminaCanzone());
        canzoniControlPanel.add(eliminaCanzoneButton);

        JButton ordinaCanzoniButton = creaButton("Ordina Canzoni");
        ordinaCanzoniButton.addActionListener(e -> ordinaCanzoni());
        canzoniControlPanel.add(ordinaCanzoniButton);

        // Pannello Playlist
        JPanel playlistPanel = new JPanel();
        tabbedPane.addTab("Playlist", null, playlistPanel, null);
        playlistPanel.setLayout(new BorderLayout());
        String[] playlistColumns = {"Nome Playlist", "Canzoni"};
        playlistTableModel = new DefaultTableModel(playlistColumns, 0);
        playlistTable = new JTable(playlistTableModel);
        setupTableAppearance(playlistTable); // Applica lo stile alla tabella
        JScrollPane playlistScrollPane = new JScrollPane(playlistTable);
        playlistPanel.add(playlistScrollPane, BorderLayout.CENTER);
        playlistTable.setBackground(Color.BLACK);
        playlistTable.setForeground(Color.WHITE);
        playlistTable.setSelectionBackground(new Color(70, 70, 70));
        playlistTable.setSelectionForeground(Color.WHITE);


        playlistPanel.add(playlistScrollPane, BorderLayout.CENTER);

        JPanel playlistControlPanel = new JPanel();
        playlistPanel.add(playlistControlPanel, BorderLayout.SOUTH);

        JButton creaPlaylistButton = creaButton("Crea Playlist");
        creaPlaylistButton.addActionListener(e -> creaPlaylist());
        playlistControlPanel.add(creaPlaylistButton);

        JButton aggiungiCanzoneAPlaylistButton = creaButton("Aggiungi Canzone a Playlist");
        aggiungiCanzoneAPlaylistButton.addActionListener(e -> aggiungiCanzoneAPlaylist());
        playlistControlPanel.add(aggiungiCanzoneAPlaylistButton);

        JButton rimuoviCanzoneDaPlaylistButton = creaButton("Rimuovi Canzone da Playlist");
        rimuoviCanzoneDaPlaylistButton.addActionListener(e -> rimuoviCanzoneDaPlaylist());
        playlistControlPanel.add(rimuoviCanzoneDaPlaylistButton);

        JButton eliminaPlaylistButton = creaButton("Elimina Playlist");
        eliminaPlaylistButton.addActionListener(e -> eliminaPlaylist());
        playlistControlPanel.add(eliminaPlaylistButton);

        JButton caricaDatiButton = creaButton("Carica Dati");
        caricaDatiButton.addActionListener(e -> caricaDati());
        playlistControlPanel.add(caricaDatiButton);

        JButton salvaDatiButton = creaButton("Salva Dati");
        salvaDatiButton.addActionListener(e -> salvaDati());
        playlistControlPanel.add(salvaDatiButton);

        frame.setVisible(true);
    }

    private void aggiungiCanzone() {
        String titolo = JOptionPane.showInputDialog(frame, "Inserisci il titolo della canzone:");
        String artista = JOptionPane.showInputDialog(frame, "Inserisci l'artista:");
        int durata = Integer.parseInt(JOptionPane.showInputDialog(frame, "Inserisci la durata (in secondi):"));
        String genere = JOptionPane.showInputDialog(frame, "Inserisci il genere:");

        Canzone canzone = new Canzone(titolo, artista, durata, genere);
        gestore.aggiungiCanzone(canzone);
        aggiornaTabellaCanzoni();
    }

    private void eliminaCanzone() {
        int selectedRow = canzoniTable.getSelectedRow();
        if (selectedRow != -1) {
            String titolo = (String) canzoniTableModel.getValueAt(selectedRow, 0);
            Canzone canzone = gestore.cercaCanzonePerTitolo(titolo);
            if (canzone != null) {
                gestore.rimuoviCanzone(canzone);
                aggiornaTabellaCanzoni();
            }
        }
    }

    private void ordinaCanzoni() {
        String[] options = {"Titolo", "Artista", "Durata"};
        int choice = JOptionPane.showOptionDialog(frame, "Scegli il criterio di ordinamento:", "Ordina Canzoni",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0:
                gestore.ordinaCanzoniPerTitolo();
                break;
            case 1:
                gestore.ordinaCanzoniPerArtista();
                break;
            case 2:
                gestore.ordinaCanzoniPerDurata();
                break;
        }
        aggiornaTabellaCanzoni();
    }

    private void creaPlaylist() {
        String nome = JOptionPane.showInputDialog(frame, "Inserisci il nome della nuova playlist:");
        gestore.creaPlaylist(nome);
        aggiornaTabellaPlaylist();
    }

    private void aggiungiCanzoneAPlaylist() {
        String nomePlaylist = JOptionPane.showInputDialog(frame, "Inserisci il nome della playlist:");
        String titoloCanzone = JOptionPane.showInputDialog(frame, "Inserisci il titolo della canzone:");
        Canzone canzone = gestore.cercaCanzonePerTitolo(titoloCanzone);
        if (canzone != null) {
            gestore.aggiungiCanzoneAPlaylist(nomePlaylist, canzone);
            aggiornaTabellaPlaylist();
        } else {
            JOptionPane.showMessageDialog(frame, "Canzone non trovata.");
        }
    }

    private void rimuoviCanzoneDaPlaylist() {
        int selectedRow = playlistTable.getSelectedRow();
        if (selectedRow != -1) {
            String nomePlaylist = (String) playlistTableModel.getValueAt(selectedRow, 0);
            String titoloCanzone = JOptionPane.showInputDialog(frame, "Inserisci il titolo della canzone da rimuovere:");

            Canzone canzone = gestore.cercaCanzonePerTitolo(titoloCanzone);
            if (canzone != null) {
                gestore.rimuoviCanzoneDaPlaylist(nomePlaylist, canzone);
                aggiornaTabellaPlaylist();
            }
        }
    }

    private void eliminaPlaylist() {
        int selectedRow = playlistTable.getSelectedRow();
        if (selectedRow != -1) {
            String nomePlaylist = (String) playlistTableModel.getValueAt(selectedRow, 0);
            Playlist playlist = gestore.cercaPlaylistPerNome(nomePlaylist);
            if (playlist != null) {
                gestore.rimuoviPlaylist(playlist);
                aggiornaTabellaPlaylist();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Seleziona una playlist da eliminare.");
        }
    }

    private void caricaDati() {
        try {
            gestore.caricaDatiDaFile("dati.txt");
            aggiornaTabellaCanzoni();
            aggiornaTabellaPlaylist();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Errore nel caricamento dei dati.");
        }
    }

    private void salvaDati() {
        try {
            gestore.salvaDatiSuFile("dati.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Errore nel salvataggio dei dati.");
        }
    }

    private void aggiornaTabellaCanzoni() {
        canzoniTableModel.setRowCount(0);
        for (Canzone canzone : gestore.getCanzoni()) {
            canzoniTableModel.addRow(new Object[]{canzone.getTitolo(), canzone.getArtista(), canzone.getDurata(), canzone.getGenere()});
        }
    }

    private void aggiornaTabellaPlaylist() {
        playlistTableModel.setRowCount(0);
        for (Playlist playlist : gestore.getPlaylist()) {
            String canzoniPlaylist = String.join(", ", playlist.getCanzoni().stream().map(Canzone::getTitolo).toArray(String[]::new));
            playlistTableModel.addRow(new Object[]{playlist.getNome(), canzoniPlaylist});
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MusicPlayerGUI window = new MusicPlayerGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
