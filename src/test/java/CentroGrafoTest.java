import org.example.Grafo;
import org.example.Floyd;
import org.example.CentroGrafo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase CentroGrafo.
 *
 * Verifica que el algoritmo identifique correctamente
 * el centro del grafo según las distancias calculadas.
 *
 * Autor: Kenett Ortega
 * Carnet: 25777
 * Universidad del Valle de Guatemala
 */
public class CentroGrafoTest {

    /**
     * Prueba que el centro del grafo se calcule correctamente
     * en un grafo simple.
     */
    @Test
    void testCentro() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);
        g.agregarArco("B", "C", 5);
        g.agregarArco("A", "C", 20);

        Floyd f = new Floyd(g);
        CentroGrafo c = new CentroGrafo(f);

        String centro = c.calcularCentro();

        // 🔥 Validación fuerte
        assertEquals("B", centro);
    }
}