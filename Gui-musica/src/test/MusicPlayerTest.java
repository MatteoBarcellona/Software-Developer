import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {

    @Test
    void testCanzone() {
        // Test: Creazione di una canzone
        Canzone canzone = new Canzone("Bohemian Rhapsody", "Queen", 354, "Rock");
        assertNotNull(canzone);
        assertEquals("Bohemian Rhapsody", canzone.getTitolo());
        assertEquals("Queen", canzone.getArtista());
        assertEquals(354, canzone.getDurata());
        assertEquals("Rock", canzone.getGenere());

        // Test: Metodo toString
        String expected = "Canzone{titolo='Bohemian Rhapsody', artista='Queen', durata=354, genere='Rock'}";
    }

    @Test
    void testPlaylist() {
        // Test: Creazione di una playlist e aggiunta di canzoni
        Playlist playlist = new Playlist("My Rock Playlist");
        Canzone song1 = new Canzone("Bohemian Rhapsody", "Queen", 354, "Rock");
        Canzone song2 = new Canzone("Stairway to Heaven", "Led Zeppelin", 482, "Rock");

        playlist.aggiungiCanzone(song1);
        playlist.aggiungiCanzone(song2);

        assertEquals(2, playlist.getCanzoni().size());
        assertTrue(playlist.getCanzoni().contains(song1));
        assertTrue(playlist.getCanzoni().contains(song2));

        // Test: Rimozione di una canzone
        playlist.rimuoviCanzone(song1);
        assertEquals(1, playlist.getCanzoni().size());
        assertFalse(playlist.getCanzoni().contains(song1));

        // Test: Metodo toString
        String expected = "Playlist{nome='My Rock Playlist', canzoni=[Stairway to Heaven]}";
    }

    @Test
    void testGestoreMusica() throws IOException {
        // Test: Aggiunta e rimozione di canzoni
        GestoreMusica gestore = new GestoreMusica();
        Canzone song1 = new Canzone("Imagine", "John Lennon", 183, "Pop");
        Canzone song2 = new Canzone("Smells Like Teen Spirit", "Nirvana", 301, "Rock");

        gestore.aggiungiCanzone(song1);
        gestore.aggiungiCanzone(song2);

        assertEquals(2, gestore.getCanzoni().size());
        assertTrue(gestore.getCanzoni().contains(song1));
        assertTrue(gestore.getCanzoni().contains(song2));

        gestore.rimuoviCanzone(song1);
        assertEquals(1, gestore.getCanzoni().size());
        assertFalse(gestore.getCanzoni().contains(song1));

        // Test: Aggiunta e rimozione di playlist
        gestore.creaPlaylist("Favourites");
        Playlist playlist = gestore.cercaPlaylistPerNome("Favourites");
        assertNotNull(playlist);

        gestore.rimuoviPlaylist(playlist);
        assertNull(gestore.cercaPlaylistPerNome("Favourites"));

        // Test: Ricerca
        gestore.aggiungiCanzone(song1);
        assertEquals(song1, gestore.cercaCanzonePerTitolo("Imagine"));
        assertNull(gestore.cercaCanzonePerTitolo("Unknown Title"));

        // Test: Ordinamento
        gestore.ordinaCanzoniPerTitolo();
        List<Canzone> sortedSongs = gestore.getCanzoni();
        assertEquals("Imagine", sortedSongs.get(0).getTitolo());
        assertEquals("Smells Like Teen Spirit", sortedSongs.get(1).getTitolo());

        // Test: Salvataggio e caricamento da file
        String testFile = "test_dati.txt";
        gestore.salvaDatiSuFile(testFile);

        GestoreMusica nuovoGestore = new GestoreMusica();
        nuovoGestore.caricaDatiDaFile(testFile);

        assertEquals(gestore.getCanzoni().size(), nuovoGestore.getCanzoni().size());
        assertEquals(gestore.getCanzoni().get(0).getTitolo(), nuovoGestore.getCanzoni().get(0).getTitolo());

        // Cleanup

    }
}
