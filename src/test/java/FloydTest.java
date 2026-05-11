import org.example.Grafo;
import org.example.Floyd;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para el algoritmo de Floyd.
 * 
 * Autor: Kenett Ortega
 * Carnet: 25777
 * Universidad del Valle de Guatemala
 */
public class FloydTest {

    /**
     * Prueba cálculo de distancia más corta.
     */
    @Test
    void testDistanciaMinima() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);
        g.agregarArco("B", "C", 5);

        Floyd f = new Floyd(g);

        assertEquals(15, f.getDistancia("A", "C"));
    }

    /**
     * Prueba reconstrucción de ruta.
     */
    @Test
    void testRuta() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);
        g.agregarArco("B", "C", 5);

        Floyd f = new Floyd(g);

        List<String> ruta = f.getRuta("A", "C");

        assertEquals(3, ruta.size());
        assertEquals("A", ruta.get(0));
        assertEquals("B", ruta.get(1));
        assertEquals("C", ruta.get(2));
    }

    /**
     * Prueba cuando no existe ruta.
     */
    @Test
    void testNoHayRuta() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);

        Floyd f = new Floyd(g);

        assertNull(f.getRuta("A", "C"));
    }
}