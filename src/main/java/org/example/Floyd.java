package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del algoritmo de Floyd-Warshall para calcular
 * las rutas más cortas entre todos los pares de nodos en un grafo dirigido.
 *
 * Este algoritmo permite obtener:
 * - La distancia mínima entre cualquier par de ciudades
 * - La ruta (camino) más corto entre ellas
 *
 * Utiliza:
 * - Una matriz de distancias (dist)
 * - Una matriz auxiliar (next) para reconstruir rutas
 *
 * Autor: Kenett Ortega
 * Carnet: 25777
 * Universidad del Valle de Guatemala
 */
public class Floyd {

    private double[][] dist;
    private int[][] next;
    private ArrayList<String> ciudades;

    private static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Constructor que recibe un grafo y ejecuta el algoritmo de Floyd.
     *
     * Inicializa las matrices de distancias y rutas,
     * y luego ejecuta el algoritmo principal.
     *
     * @param grafo grafo sobre el cual se calcularán las rutas más cortas
     */
    public Floyd(Grafo grafo) {
        this.ciudades = grafo.getCiudades();

        int n = ciudades.size();
        dist = new double[n][n];
        next = new int[n][n];

        double[][] matriz = grafo.getMatriz();

        // Inicialización de matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = matriz[i][j];

                if (matriz[i][j] != INF && i != j) {
                    next[i][j] = j;
                } else {
                    next[i][j] = -1;
                }
            }
        }

        floydWarshall();
    }

    /**
     * Implementación del algoritmo de Floyd-Warshall.
     *
     * Actualiza la matriz de distancias mínimas considerando
     * todos los posibles nodos intermedios.
     */
    private void floydWarshall() {
        int n = dist.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if (dist[i][k] == INF || dist[k][j] == INF) continue;

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    /**
     * Retorna la distancia mínima entre dos ciudades.
     *
     * @param origen nombre de la ciudad de origen
     * @param destino nombre de la ciudad destino
     * @return distancia mínima entre origen y destino,
     *         o infinito si no existe camino
     */
    public double getDistancia(String origen, String destino) {
        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        if (i == -1 || j == -1) return INF;

        return dist[i][j];
    }

    /**
     * Reconstruye la ruta más corta entre dos ciudades.
     *
     * @param origen ciudad de origen
     * @param destino ciudad destino
     * @return lista de ciudades que representan la ruta más corta,
     *         o null si no existe camino
     */
    public List<String> getRuta(String origen, String destino) {
        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        if (i == -1 || j == -1 || next[i][j] == -1) {
            return null;
        }

        List<String> ruta = new ArrayList<>();
        ruta.add(origen);

        while (i != j) {
            i = next[i][j];
            ruta.add(ciudades.get(i));
        }

        return ruta;
    }

    /**
     * Retorna la matriz de distancias mínimas calculadas.
     *
     * @return matriz de distancias
     */
    public double[][] getDist() {
        return dist;
    }

    /**
     * Retorna la lista de ciudades del grafo.
     *
     * @return lista de ciudades
     */
    public ArrayList<String> getCiudades() {
        return ciudades;
    }
}