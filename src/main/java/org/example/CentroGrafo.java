package org.example;

import java.util.ArrayList;

/**
 * Clase encargada de calcular el centro de un grafo dirigido.
 *
 * El centro del grafo es el nodo cuya distancia máxima hacia
 * todos los demás nodos es la menor posible.
 *
 * Se basa en la matriz de distancias obtenida mediante
 * el algoritmo de Floyd-Warshall.
 *
 * Autor: Kenett Ortega
 * Carnet: 25777
 * Universidad del Valle de Guatemala
 */
public class CentroGrafo {

    private double[][] dist;
    private ArrayList<String> ciudades;

    private static final double INF = Double.POSITIVE_INFINITY;

    /**
     * Constructor que recibe un objeto Floyd con las distancias calculadas.
     *
     * @param floyd objeto que contiene la matriz de distancias mínimas
     */
    public CentroGrafo(Floyd floyd) {
        this.dist = floyd.getDist();
        this.ciudades = floyd.getCiudades();
    }

    /**
     * Calcula el centro del grafo.
     *
     * El proceso consiste en:
     * 1. Para cada nodo, encontrar la distancia máxima hacia otros nodos
     * 2. Elegir el nodo cuya distancia máxima sea la menor
     *
     * @return nombre de la ciudad que representa el centro del grafo,
     *         o null si no se puede determinar
     */
    public String calcularCentro() {
        int n = dist.length;

        double minExcentricidad = INF;
        int indiceCentro = -1;

        for (int i = 0; i < n; i++) {

            double maxDist = 0;

            for (int j = 0; j < n; j++) {
                // 🔥 ignoramos caminos inexistentes
                if (dist[i][j] != INF && dist[i][j] > maxDist) {
                    maxDist = dist[i][j];
                }
            }

            if (maxDist < minExcentricidad) {
                minExcentricidad = maxDist;
                indiceCentro = i;
            }
        }

        if (indiceCentro == -1) return null;

        return ciudades.get(indiceCentro);
    }
}