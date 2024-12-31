import java.util.HashMap;

public class TestPersistenza {
    public static void main(String[] args) {
        SimulazioneDatabase database = new SimulazioneDatabase();

        // Aggiungi un prodotto
        Prodotto prodotto = new Prodotto(1, "Prodotto 1", 10.0, 100);
        database.aggiungiProdotto(prodotto);

        // Aggiungi un ordine
        Ordine ordine = new Ordine(1, "Cliente 1", new HashMap<>());
        ordine.aggiungiProdotto(prodotto, 2);
        database.aggiungiOrdine(ordine);

        // Salva i dati
        database.salvaProdotti();
        database.salvaOrdini();

        // Carica i dati (simula un nuovo avvio)
        database.caricaProdotti();
        database.caricaOrdini();

        // Verifica che i dati siano correttamente caricati
        System.out.println("Prodotti caricati: " + database.getProdotti().size());
        System.out.println("Ordini caricati: " + database.getOrdini().size());
    }
}
