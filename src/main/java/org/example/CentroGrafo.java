package org.example;

import java.util.ArrayList;

/**
 * Clase para calcular el centro del grafo usando
 * la matriz de distancias obtenida con Floyd.
 */
public class CentroGrafo {

    private double[][] dist;
    private ArrayList<String> ciudades;

    private static final double INF = Double.POSITIVE_INFINITY;

    public CentroGrafo(Floyd floyd) {
        this.dist = floyd.dist; // usamos la matriz ya calculada
        this.ciudades = floyd.ciudades;
    }

    /**
     * Calcula el centro del grafo.
     *
     * @return nombre de la ciudad centro
     */
    public String calcularCentro() {
        int n = dist.length;

        double minExcentricidad = INF;
        int indiceCentro = -1;

        for (int i = 0; i < n; i++) {

            double maxDist = 0;

            for (int j = 0; j < n; j++) {
                if (dist[i][j] > maxDist) {
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