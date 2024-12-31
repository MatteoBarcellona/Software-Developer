import java.util.HashMap;
import java.util.Map;

public class Ordine {
    private int id;
    private String nomeCliente;
    private Map<Prodotto, Integer> prodotti;  // La mappa che associa un prodotto alla sua quantità
    private double totaleOrdine;

    // Costruttore
    public Ordine(int id, String nomeCliente, Map<Prodotto, Integer> prodotti) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.prodotti = prodotti;
        this.totaleOrdine = calcolaTotale(); // Calcola il totale iniziale dell'ordine
        this.prodotti = new HashMap<>();
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Map<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    public void setProdotti(Map<Prodotto, Integer> prodotti) {
        this.prodotti = prodotti;
        this.totaleOrdine = calcolaTotale(); // Ricalcola il totale quando i prodotti cambiano
    }

    // Metodo per aggiungere un prodotto all'ordine
    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
        // Se il prodotto è già presente nell'ordine, incrementa la quantità
        if (prodotti.containsKey(prodotto)) {
            int nuovaQuantita = prodotti.get(prodotto) + quantita;
            prodotti.put(prodotto, nuovaQuantita);
        } else {
            // Se il prodotto non è presente, aggiungilo con la quantità
            prodotti.put(prodotto, quantita);
        }

        // Ricalcola il totale dell'ordine
        this.totaleOrdine = calcolaTotale();
    }

    // Metodo per calcolare il totale dell'ordine
    public double calcolaTotale() {
        double totale = 0;
        for (Map.Entry<Prodotto, Integer> entry : prodotti.entrySet()) {
            totale += entry.getKey().getPrezzo() * entry.getValue();
        }
        return totale;
    }

    // Metodo per rimuovere un prodotto dall'ordine
    public void rimuoviProdotto(Prodotto prodotto) {
        if (prodotti.containsKey(prodotto)) {
            prodotti.remove(prodotto);
            this.totaleOrdine = calcolaTotale(); // Ricalcola il totale dopo la rimozione
        }
    }

    // Metodo per ottenere il totale dell'ordine
    public double getTotaleOrdine() {
        return totaleOrdine;
    }
}
