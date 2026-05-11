package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 * Programa principal que maneja el grafo, aplica Floyd
 * y permite interacción con el usuario.
 */
public class MainGrafo {

    public static void main(String[] args) {

        Grafo grafo = new Grafo();

        // 1. Leer archivo
        try {
            BufferedReader br = new BufferedReader(new FileReader("guategrafo.txt"));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" ");

                String origen = partes[0];
                String destino = partes[1];
                double distancia = Double.parseDouble(partes[2]);

                grafo.agregarArco(origen, destino, distancia);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error leyendo archivo");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            Floyd floyd = new Floyd(grafo);
            CentroGrafo centro = new CentroGrafo(floyd);

            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Ruta más corta");
            System.out.println("2. Centro del grafo");
            System.out.println("3. Modificar grafo");
            System.out.println("4. Salir");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1:
                    System.out.print("Ciudad origen: ");
                    String origen = sc.nextLine();

                    System.out.print("Ciudad destino: ");
                    String destino = sc.nextLine();

                    double distancia = floyd.getDistancia(origen, destino);
                    List<String> ruta = floyd.getRuta(origen, destino);

                    if (ruta == null) {
                        System.out.println("No existe ruta.");
                    } else {
                        System.out.println("Distancia: " + distancia);
                        System.out.println("Ruta: " + ruta);
                    }
                    break;

                case 2:
                    System.out.println("Centro del grafo: " + centro.calcularCentro());
                    break;

                case 3:
                    System.out.println("1. Eliminar conexión");
                    System.out.println("2. Agregar conexión");

                    int subop = sc.nextInt();
                    sc.nextLine();

                    if (subop == 1) {
                        System.out.print("Origen: ");
                        String o = sc.nextLine();

                        System.out.print("Destino: ");
                        String d = sc.nextLine();

                        grafo.eliminarArco(o, d);
                        System.out.println("Conexión eliminada.");

                    } else if (subop == 2) {
                        System.out.print("Origen: ");
                        String o = sc.nextLine();

                        System.out.print("Destino: ");
                        String d = sc.nextLine();

                        System.out.print("Distancia: ");
                        double dist = sc.nextDouble();
                        sc.nextLine();

                        grafo.agregarArco(o, d, dist);
                        System.out.println("Conexión agregada.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 4);

        sc.close();
    }
}