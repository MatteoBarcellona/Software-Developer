import java.util.HashMap;
import java.util.Map;

public class GestoreOrdini {
    private SimulazioneDatabase database;

    public GestoreOrdini(SimulazioneDatabase database) {
        this.database = database;
    }

    public void creaOrdine(int id, String nomeCliente, Map<Integer, Integer> prodottiRichiesti) {
        Map<Prodotto, Integer> prodotti = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : prodottiRichiesti.entrySet()) {
            Prodotto prodotto = database.leggiProdotto(entry.getKey());
            if (prodotto == null) {
                throw new IllegalArgumentException("Prodotto non trovato: ID " + entry.getKey());
            }
            prodotti.put(prodotto, entry.getValue());
        }
        Ordine ordine = new Ordine(id, nomeCliente, prodotti);
        database.aggiungiOrdine(ordine);
    }
}
