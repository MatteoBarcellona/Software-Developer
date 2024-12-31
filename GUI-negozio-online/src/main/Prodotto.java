public class Prodotto {
    private int id;
    private String nome;
    private double prezzo;
    private int quantitaDisponibile;

    public Prodotto(int id, String nome, double prezzo, int quantitaDisponibile) {
        if (id <= 0 || prezzo < 0 || quantitaDisponibile < 0 || nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Dati del prodotto non validi.");
        }
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantitaDisponibile = quantitaDisponibile;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantitaDisponibile() {
        return quantitaDisponibile;
    }

    public void incrementaQuantita(int quantita) {
        this.quantitaDisponibile += quantita;
    }

    public void decrementaQuantita(int quantita) {
        if (quantita > quantitaDisponibile) {
            throw new IllegalArgumentException("Quantità insufficiente.");
        }
        this.quantitaDisponibile -= quantita;
    }

    @Override
    public String toString() {
        return "Prodotto{id=" + id + ", nome='" + nome + "', prezzo=" + prezzo +
                ", quantitaDisponibile=" + quantitaDisponibile + "}";
    }

    public void setQuantitaDisponibile(int nuovaQuantita) {
             this.quantitaDisponibile = nuovaQuantita;
    }
}
