import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProdottoTest {

    @Test
    void testCreazioneProdottoValido() {
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.50, 100);
        assertNotNull(prodotto);
        assertEquals(1, prodotto.getId());
        assertEquals("Prodotto A", prodotto.getNome());
        assertEquals(10.50, prodotto.getPrezzo());
        assertEquals(100, prodotto.getQuantitaDisponibile());
    }

    @Test
    void testAggiornamentoQuantita() {
        Prodotto prodotto = new Prodotto(1, "Prodotto A", 10.50, 100);
        prodotto.incrementaQuantita(50);
        assertEquals(150, prodotto.getQuantitaDisponibile());
        prodotto.decrementaQuantita(30);
        assertEquals(120, prodotto.getQuantitaDisponibile());
    }

    @Test
    void testValoriNonValidi() {
        assertThrows(IllegalArgumentException.class, () -> new Prodotto(-1, "Prodotto A", 10.50, 100));
        assertThrows(IllegalArgumentException.class, () -> new Prodotto(1, "", 10.50, 100));
        assertThrows(IllegalArgumentException.class, () -> new Prodotto(1, "Prodotto A", -5.00, 100));
        assertThrows(IllegalArgumentException.class, () -> new Prodotto(1, "Prodotto A", 10.50, -10));
    }
}
