package org.example;

import java.util.*;

/**
 * Implementación de un grafo dirigido usando matriz de adyacencia.
 * Los nodos son ciudades y los arcos representan distancias en KM.
 */
public class Grafo {

    private Map<String, Integer> indices;
    private ArrayList<String> ciudades;
    private double[][] matriz;

    private static final double INF = Double.POSITIVE_INFINITY;

    public Grafo() {
        indices = new HashMap<>();
        ciudades = new ArrayList<>();
        matriz = new double[0][0];
    }

    /**
     * Agrega una ciudad al grafo si no existe.
     */
    private void agregarCiudad(String nombre) {
        if (!indices.containsKey(nombre)) {
            indices.put(nombre, ciudades.size());
            ciudades.add(nombre);
            expandirMatriz();
        }
    }

    /**
     * Expande la matriz de adyacencia cuando se agrega una nueva ciudad.
     */
    private void expandirMatriz() {
        int n = ciudades.size();
        double[][] nueva = new double[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(nueva[i], INF);
            nueva[i][i] = 0;
        }

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                nueva[i][j] = matriz[i][j];
            }
        }

        matriz = nueva;
    }

    /**
     * Agrega una conexión dirigida entre dos ciudades.
     */
    public void agregarArco(String origen, String destino, double distancia) {
        agregarCiudad(origen);
        agregarCiudad(destino);

        int i = indices.get(origen);
        int j = indices.get(destino);

        matriz[i][j] = distancia;
    }

    /**
     * Elimina la conexión entre dos ciudades.
     */
    public void eliminarArco(String origen, String destino) {
        if (indices.containsKey(origen) && indices.containsKey(destino)) {
            int i = indices.get(origen);
            int j = indices.get(destino);

            matriz[i][j] = INF;
        }
    }

    /**
     * Retorna la matriz de adyacencia.
     */
    public double[][] getMatriz() {
        return matriz;
    }

    /**
     * Retorna la lista de ciudades.
     */
    public ArrayList<String> getCiudades() {
        return ciudades;
    }

    /**
     * Muestra la matriz (debug).
     */
    public void imprimirMatriz() {
        int n = matriz.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matriz[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(matriz[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}