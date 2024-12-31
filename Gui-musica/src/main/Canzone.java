public class Canzone {
    private String titolo;
    private String artista;
    private int durata;
    private String genere;

    public Canzone(String titolo, String artista, int durata, String genere) {
        this.titolo = titolo;
        this.artista = artista;
        this.durata = durata;
        this.genere = genere;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return titolo + " - " + artista + " (" + durata + "s) [" + genere + "]";
    }
}
