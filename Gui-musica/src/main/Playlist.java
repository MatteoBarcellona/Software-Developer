import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playlist {
    private String nome;
    private List<Canzone> listaCanzoni;

    public Playlist(String nome) {
        this.nome = nome;
        this.listaCanzoni = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Canzone> getListaCanzoni() {
        return listaCanzoni;
    }

    public void aggiungiCanzone(Canzone canzone) {
        listaCanzoni.add(canzone);
    }

    public void rimuoviCanzone(Canzone canzone) {
        listaCanzoni.remove(canzone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(nome + ":\n");
        for (Canzone canzone : listaCanzoni) {
            sb.append("- " + canzone.toString() + "\n");
        }
        return sb.toString();
    }

    public List<Canzone> getCanzoni() {
        return listaCanzoni;
    }
}
