import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimulazioneDatabase {
    private Map<Integer, Prodotto> prodotti = new HashMap<>();
    private Map<Integer, Ordine> ordini = new HashMap<>();
    private Map<Integer, String> associazioneProdottoCliente;

    public SimulazioneDatabase() {
        caricaProdotti(); // Carica i prodotti
        caricaOrdini();   // Carica gli ordini
    }

    private static final String FILE_PRODOTTI = "prodotti.json";
    private static final String FILE_ORDINI = "ordini.json";
    // CRUD per Prodotti


    public void salvaProdotti() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_PRODOTTI), prodotti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto.getId())) {
            throw new IllegalArgumentException("Prodotto con ID già esistente.");
        }
        prodotti.put(prodotto.getId(), prodotto);
        salvaProdotti(); // Salva i prodotti dopo l'aggiunta
    }


    public Prodotto leggiProdotto(int id) {
        return prodotti.get(id);
    }

    public void aggiornaProdotto(Prodotto prodotto) {
        if (!prodotti.containsKey(prodotto.getId())) {
            throw new IllegalArgumentException("Prodotto non trovato.");
        }
        prodotti.put(prodotto.getId(), prodotto);
    }

    public void eliminaProdotto(int id) {
        if (!prodotti.containsKey(id)) {
            throw new IllegalArgumentException("Prodotto non trovato.");
        }
        prodotti.remove(id);
    }

    public Map<Integer, Prodotto> getProdotti() {
        return prodotti;
    }

    public void associaClienteAProdotto(int idProdotto, String nomeCliente) {
        associazioneProdottoCliente.put(idProdotto, nomeCliente);
    }

    // CRUD per Ordini
    public void aggiungiOrdine(Ordine ordine) {
        if (ordini.containsKey(ordine.getId())) {
            throw new IllegalArgumentException("Ordine con ID già esistente.");
        }
        for (Map.Entry<Prodotto, Integer> entry : ordine.getProdotti().entrySet()) {
            entry.getKey().decrementaQuantita(entry.getValue());
        }
        ordini.put(ordine.getId(), ordine);
    }

    public Ordine leggiOrdine(int id) {
        return ordini.get(id);
    }

    public void eliminaOrdine(int id) {
        Ordine ordine = ordini.remove(id);
        if (ordine == null) {
            throw new IllegalArgumentException("Ordine non trovato.");
        }
        for (Map.Entry<Prodotto, Integer> entry : ordine.getProdotti().entrySet()) {
            entry.getKey().incrementaQuantita(entry.getValue());
        }
    }


    public void caricaProdotti() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            prodotti = objectMapper.readValue(new File(FILE_PRODOTTI), objectMapper.getTypeFactory().constructMapType(Map.class, Integer.class, Prodotto.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per salvare gli ordini
    public void salvaOrdini() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(FILE_ORDINI), ordini);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per caricare gli ordini
    public void caricaOrdini() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ordini = objectMapper.readValue(new File(FILE_ORDINI), objectMapper.getTypeFactory().constructMapType(Map.class, Integer.class, Ordine.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Ordine> getOrdini() {
        return ordini;
    }

    public String getClienteAssociato(int id) {
        return associazioneProdottoCliente.getOrDefault(id, "N/A");
    }
}
