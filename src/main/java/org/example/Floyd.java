package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del algoritmo de Floyd-Warshall
 * para encontrar rutas más cortas entre todos los pares de nodos.
 */
public class Floyd {

    private double[][] dist;
    private int[][] next;
    private ArrayList<String> ciudades;

    private static final double INF = Double.POSITIVE_INFINITY;

    public Floyd(Grafo grafo) {
        this.ciudades = grafo.getCiudades();

        int n = ciudades.size();
        dist = new double[n][n];
        next = new int[n][n];

        double[][] matriz = grafo.getMatriz();

        // Inicialización
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
     * Algoritmo principal de Floyd-Warshall
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
     * Retorna la distancia mínima entre dos ciudades
     */
    public double getDistancia(String origen, String destino) {
        int i = ciudades.indexOf(origen);
        int j = ciudades.indexOf(destino);

        if (i == -1 || j == -1) return INF;

        return dist[i][j];
    }

    /**
     * Reconstruye la ruta más corta entre dos ciudades
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
     * Imprime la matriz de distancias (debug)
     */
    public void imprimirDistancias() {
        int n = dist.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}