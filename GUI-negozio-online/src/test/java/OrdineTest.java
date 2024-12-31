import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class OrdineTest {

    @Test
    void testCreazioneOrdineValido() {
        Prodotto prodotto1 = new Prodotto(1, "Prodotto A", 10.50, 100);
        Prodotto prodotto2 = new Prodotto(2, "Prodotto B", 5.00, 50);
        Map<Prodotto, Integer> prodotti = Map.of(prodotto1, 2, prodotto2, 3);
        Ordine ordine = new Ordine(1, "Cliente 1", prodotti);


    }

    @Test
    void testCalcoloTotaleOrdine() {
        Prodotto prodotto1 = new Prodotto(1, "Prodotto A", 10.50, 100);
        Prodotto prodotto2 = new Prodotto(2, "Prodotto B", 5.00, 50);
        Map<Prodotto, Integer> prodotti = Map.of(prodotto1, 2, prodotto2, 3);

        Ordine ordine = new Ordine(1, "Cliente 1", prodotti);
        assertEquals(2 * 10.50 + 3 * 5.00, ordine.getTotaleOrdine());
    }

    @Test
    void testOrdineConProdottiNonDisponibili() {
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.50, 2);
        Map<Prodotto, Integer> prodotti = Map.of(prodotto, 5);

    }
}
