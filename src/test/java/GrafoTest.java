import org.example.Grafo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Grafo.
 * 
 * Autor: Kenett Ortega
 * Carnet: 25777
 * Universidad del Valle de Guatemala
 */
public class GrafoTest {

    /**
     * Prueba agregar conexiones al grafo.
     */
    @Test
    void testAgregarArco() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);

        double[][] matriz = g.getMatriz();

        assertEquals(10, matriz[0][1]);
    }

    /**
     * Prueba eliminar conexión.
     */
    @Test
    void testEliminarArco() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 10);
        g.eliminarArco("A", "B");

        double[][] matriz = g.getMatriz();

        assertEquals(Double.POSITIVE_INFINITY, matriz[0][1]);
    }

    /**
     * Prueba agregar múltiples nodos dinámicamente.
     */
    @Test
    void testAgregarMultiplesNodos() {
        Grafo g = new Grafo();

        g.agregarArco("A", "B", 5);
        g.agregarArco("B", "C", 7);

        assertEquals(3, g.getCiudades().size());
    }
}