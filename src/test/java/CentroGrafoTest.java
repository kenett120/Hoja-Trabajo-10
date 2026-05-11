import org.example.Grafo;
import org.example.Floyd;
import org.example.CentroGrafo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CentroGrafoTest {

    @Test
    void testCentro() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);
        g.agregarArco("B", "C", 5);
        g.agregarArco("A", "C", 20);

        Floyd f = new Floyd(g);
        CentroGrafo c = new CentroGrafo(f);

        String centro = c.calcularCentro();

        assertNotNull(centro);
    }
}