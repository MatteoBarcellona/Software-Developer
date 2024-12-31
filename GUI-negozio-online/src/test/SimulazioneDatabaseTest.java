import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class SimulazioneDatabaseTest {
    @Test
    public void testAggiungiProdotto() {
        SimulazioneDatabase db = new SimulazioneDatabase();
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.0, 5);
        db.aggiungiProdotto(prodotto);

        assertEquals(prodotto, db.leggiProdotto(1));
    }

    @Test
    public void testEliminaProdotto() {
        SimulazioneDatabase db = new SimulazioneDatabase();
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.0, 5);
        db.aggiungiProdotto(prodotto);
        db.eliminaProdotto(1);

        assertNull(db.leggiProdotto(1));
    }


    @Test
    void testAggiuntaRimozioneProdotti() {
        SimulazioneDatabase db = new SimulazioneDatabase();
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.50, 100);

        db.aggiungiProdotto(prodotto);
        assertEquals(1, db.getProdotti().size());
        assertEquals(prodotto, db.leggiProdotto(1));

        db.eliminaProdotto(1);
        assertEquals(0, db.getProdotti().size());
    }

    @Test
    void testAggiuntaRimozioneOrdini() {
        SimulazioneDatabase db = new SimulazioneDatabase();
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.50, 100);
        db.aggiungiProdotto(prodotto);

        Map<Integer, Integer> prodottiRichiesti = Map.of(1, 5);
        GestoreOrdini gestore = new GestoreOrdini(db);
        gestore.creaOrdine(1, "Cliente 1", prodottiRichiesti);


    }

    @Test
    void testConflittiQuantitaDisponibili() {
        SimulazioneDatabase db = new SimulazioneDatabase();
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.50, 2);
        db.aggiungiProdotto(prodotto);

        Map<Integer, Integer> prodottiRichiesti = Map.of(1, 5);
        GestoreOrdini gestore = new GestoreOrdini(db);

    }
}
