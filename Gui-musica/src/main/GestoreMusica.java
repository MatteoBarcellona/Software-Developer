import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class GestoreMusica {
    private List<Canzone> elencoCanzoni;
    private List<Playlist> elencoPlaylist;

    public GestoreMusica() {
        elencoCanzoni = new ArrayList<>();
        elencoPlaylist = new ArrayList<>();
    }

    public void aggiungiCanzone(Canzone canzone) {
        elencoCanzoni.add(canzone);
    }

    public void rimuoviCanzone(Canzone canzone) {
        elencoCanzoni.remove(canzone);
    }

    public Canzone cercaCanzonePerTitolo(String titolo) {
        return elencoCanzoni.stream()
                .filter(c -> c.getTitolo().equalsIgnoreCase(titolo))
                .findFirst().orElse(null);
    }

    public Canzone cercaCanzonePerArtista(String artista) {
        return elencoCanzoni.stream()
                .filter(c -> c.getArtista().equalsIgnoreCase(artista))
                .findFirst().orElse(null);
    }

    public void ordinaCanzoniPerTitolo() {
        elencoCanzoni.sort(Comparator.comparing(Canzone::getTitolo));
    }

    public void ordinaCanzoniPerArtista() {
        elencoCanzoni.sort(Comparator.comparing(Canzone::getArtista));
    }

    public void ordinaCanzoniPerDurata() {
        elencoCanzoni.sort(Comparator.comparingInt(Canzone::getDurata));
    }

    public void creaPlaylist(String nome) {
        elencoPlaylist.add(new Playlist(nome));
    }

    public void aggiungiCanzoneAPlaylist(String nomePlaylist, Canzone canzone) {
        Playlist playlist = trovaPlaylist(nomePlaylist);
        if (playlist != null) {
            playlist.aggiungiCanzone(canzone);
        }
    }

    public void rimuoviCanzoneDaPlaylist(String nomePlaylist, Canzone canzone) {
        Playlist playlist = trovaPlaylist(nomePlaylist);
        if (playlist != null) {
            playlist.rimuoviCanzone(canzone);
        }
    }

    private Playlist trovaPlaylist(String nomePlaylist) {
        return elencoPlaylist.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nomePlaylist))
                .findFirst().orElse(null);
    }

    public void salvaDatiSuFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("[Canzoni]\n");
            for (Canzone canzone : elencoCanzoni) {
                writer.write(canzone.getTitolo() + ";" + canzone.getArtista() + ";" + canzone.getDurata() + ";" + canzone.getGenere() + "\n");
            }
            writer.write("[Playlist]\n");
            for (Playlist playlist : elencoPlaylist) {
                writer.write(playlist.getNome() + ";");
                for (Canzone canzone : playlist.getListaCanzoni()) {
                    writer.write(canzone.getTitolo() + ",");
                }
                writer.write("\n");
            }
        }
    }

    public void caricaDatiDaFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean inCanzoni = false, inPlaylist = false;

            while ((line = reader.readLine()) != null) {
                if (line.equals("[Canzoni]")) {
                    inCanzoni = true;
                    inPlaylist = false;
                } else if (line.equals("[Playlist]")) {
                    inCanzoni = false;
                    inPlaylist = true;
                } else {
                    if (inCanzoni) {
                        String[] parts = line.split(";");
                        Canzone canzone = new Canzone(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3]);
                        elencoCanzoni.add(canzone);
                    } else if (inPlaylist) {
                        String[] parts = line.split(";");
                        Playlist playlist = new Playlist(parts[0]);
                        if (parts.length > 1) {
                            String[] canzoniTitoli = parts[1].split(",");
                            for (String titolo : canzoniTitoli) {
                                Canzone canzone = cercaCanzonePerTitolo(titolo);
                                if (canzone != null) {
                                    playlist.aggiungiCanzone(canzone);
                                }
                            }
                        }
                        elencoPlaylist.add(playlist);
                    }
                }
            }
        }
    }

    public void mostraTutteLeCanzoni() {
        for (Canzone canzone : elencoCanzoni) {
            System.out.println(canzone);
        }
    }

    public void mostraTutteLePlaylist() {
        for (Playlist playlist : elencoPlaylist) {
            System.out.println(playlist);
        }
    }

    public List<Playlist> getPlaylist() {
        return elencoPlaylist;
    }

    public void rimuoviPlaylist(Playlist playlist) {
        elencoPlaylist.remove(playlist);
    }

    public List<Canzone> getCanzoni() {
        return elencoCanzoni;
    }

    public Playlist cercaPlaylistPerNome(String nomePlaylist) {
        // Scorre la lista delle playlist e cerca quella con il nome corrispondente
        for (Playlist playlist : elencoPlaylist) {
            if (playlist.getNome().equalsIgnoreCase(nomePlaylist)) {
                return playlist; // Restituisce la playlist trovata
            }
        }
        return null; // Se la playlist non è trovata, restituisce null
    }

}
